package com.example.movies.news.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movies.common.data.dataprefence.DataPreference
import com.example.movies.news.data.model.News
import com.example.movies.news.domain.GetNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: GetNewsRepository,
    private val dataPreference: DataPreference
) : ViewModel() {

    private val _currentCategory = MutableStateFlow<String?>(null)
    val currentCategory = _currentCategory.asStateFlow()


    val newsFlow: Flow<PagingData<News>> = _currentCategory
        .filterNotNull()
        .flatMapLatest { category ->
            repository.getNewsPager(category)
                .cachedIn(viewModelScope)
        }

    init {
        viewModelScope.launch {
            val savedCategory = dataPreference.categoryState.first()
            _currentCategory.value = savedCategory
        }
    }

    fun saveCategory(newCategory: String) {
        viewModelScope.launch {
            if (_currentCategory.value != newCategory) {
                _currentCategory.value = newCategory
                dataPreference.SaveCategoryState(newCategory)
            }
        }
    }
}
