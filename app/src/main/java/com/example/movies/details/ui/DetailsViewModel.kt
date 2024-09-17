package com.example.movies.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.details.data.model.NewsId
import com.example.movies.details.domain.GetNewsRepositoryById
import com.example.movies.shared.data.room.FavoriteNewEntity
import com.example.movies.shared.domain.DatabaseRepository
import com.example.movies.shared.until.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: GetNewsRepositoryById,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    private val _newsById = MutableStateFlow<NewsId?>(null)
    val newsById = _newsById.asStateFlow()


    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> get() = _isFavorite

    fun getNewsById(id: Int) {
        viewModelScope.launch {
            when (val result = repository.getNewsById(id)) {
                is Result.Success -> {
                    _newsById.value = result.data
                    checkIfFavorite(result.data.id)
                }
                is Result.Error -> {
                    _error.value = "Произошла ошибка:"
                }
            }
        }
    }




    private suspend fun checkIfFavorite(newsId: Int) {
        _isFavorite.value = databaseRepository.isFavorite(newsId)
    }


    fun toggleFavorite(news: NewsId?) {
        viewModelScope.launch {
            news?.let {
                val isCurrentlyFavorite = _isFavorite.value
                if (isCurrentlyFavorite) {
                    databaseRepository.delete(
                        it.id
                    )
                } else {
                    databaseRepository.insert(
                        FavoriteNewEntity(
                            id = 0,
                            apiId = it.id,
                            title = it.title,
                            content = it.content,
                            image = it.image,
                            createdAt = it.createdAt,
                            link = it.link
                        )
                    )
                }
                _isFavorite.value = !isCurrentlyFavorite
            }
        }
    }


    fun clearError() {
        _error.value = null
    }
}
