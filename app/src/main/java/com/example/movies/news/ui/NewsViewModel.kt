import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movies.news.data.model.News
import com.example.movies.news.domain.GetNewsRepository
import com.example.movies.shared.data.dataprefence.DataPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import java.io.IOException

class NewsViewModel(
    private val repository: GetNewsRepository,
    private val dataPreference: DataPreference
) : ViewModel() {

    private val _currentCategory = MutableStateFlow<String?>(null)
    val currentCategory = _currentCategory.asStateFlow()

    private val _newsError = MutableStateFlow<String?>(null)
    val newsError = _newsError.asStateFlow()


    val newsFlow: Flow<PagingData<News>> = _currentCategory
        .filterNotNull()
        .flatMapLatest { category ->
            repository.getNewsPager(category)
                .catch { e ->
                    _newsError.value = when (e) {
                        is IOException -> "Ошибка сети при загрузке новостей"
                        else -> "Произошла ошибка при загрузке новостей"
                    }
                    emit(PagingData.empty())
                }
                .cachedIn(viewModelScope)
        }

    init {
        viewModelScope.launch {
            // Загрузка сохранённой категории при инициализации
            val savedCategory = dataPreference.categoryState.first()
            _currentCategory.value = savedCategory
        }
    }

    // Сохранение новой категории
    fun saveCategory(newCategory: String) {
        viewModelScope.launch {
            if (_currentCategory.value != newCategory) {
                _currentCategory.value = newCategory
                try {
                    // Сохранение категории в DataStore
                    dataPreference.SaveCategoryState(newCategory)
                } catch (e: Exception) {
                    // Обработка ошибок при сохранении категории
                    _newsError.value = "Ошибка при сохранении категории"
                }
            }
        }
    }

    // Очистка ошибки
    fun clearError() {
        _newsError.value = null
    }
}
