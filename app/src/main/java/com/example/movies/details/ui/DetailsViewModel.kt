package com.example.movies.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.common.data.api.State
import com.example.movies.common.data.room.FavoriteNewEntity
import com.example.movies.common.repository.DatabaseRepository
import com.example.movies.details.data.model.NewsId
import com.example.movies.details.domain.GetNewsRepositoryById
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: GetNewsRepositoryById,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {


    private val _state = MutableStateFlow<State<NewsId>>(State.Loading)
    val state: StateFlow<State<NewsId>> get() = _state.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> get() = _isFavorite.asStateFlow()

    fun getNewsById(id: Int) {
        viewModelScope.launch {
            _state.value = State.Loading
            when (val result = repository.getNewsById(id)) {
                is State.Success -> {
                    checkIfFavorite(result.data.id)
                    _state.value = State.Success(result.data)
                }
                is State.Error -> {
                    _state.value = State.Error(result.error)
                }

                State.Loading -> TODO()
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
                    databaseRepository.delete(it.id)
                } else {
                    databaseRepository.insert(
                        FavoriteNewEntity(
                            id = 0,
                            apiId = it.id,
                            title = it.title,
                            content = it.content,
                            image = it.image,
                        )
                    )
                }
                _isFavorite.value = !isCurrentlyFavorite
            }
        }
    }
}
