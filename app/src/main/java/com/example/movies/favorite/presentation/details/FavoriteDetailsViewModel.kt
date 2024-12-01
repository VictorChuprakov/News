package com.example.movies.favorite.presentation.details


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.common.data.room.entity.FavoriteNewsEntity
import com.example.movies.common.domain.FavoriteNewsRepository
import com.example.movies.news.domain.details.GetNewsByIdRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = FavoriteDetailsViewModel.FavoriteDetailsViewModelFactory::class)
class FavoriteDetailsViewModel @AssistedInject constructor(
    @Assisted private val newsId: Int,
    private val repository: GetNewsByIdRepository,
    private val favoriteNewsRepository: FavoriteNewsRepository
) : ViewModel() {

    private val _state = MutableStateFlow<FavoriteNewsEntity?>(null)
    val state = _state.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite.asStateFlow()

    init {
        fetchNewsDetails(newsId)
        checkIfFavorite(newsId)
    }

    @AssistedFactory
    interface FavoriteDetailsViewModelFactory {
        fun create(newsId: Int): FavoriteDetailsViewModel
    }

    private fun fetchNewsDetails(newsId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val newsResult = favoriteNewsRepository.getNewsById(newsId)
            _state.value = newsResult
        }
    }

    private fun checkIfFavorite(apiId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val favorite = favoriteNewsRepository.getFavoriteNewsById(apiId)
            _isFavorite.value = favorite != null
        }
    }

    private fun addToFavorites(favoriteNewsEntity: FavoriteNewsEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteNewsRepository.addFavoriteNews(favoriteNewsEntity)
            _isFavorite.value = true
        }
    }

    private fun removeFromFavorites(apiId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteNewsRepository.removeFavoriteNews(apiId)
            _isFavorite.value = false
        }
    }

    fun toggleFavorite(favoriteNewsEntity: FavoriteNewsEntity) {
        if (_isFavorite.value) {
            removeFromFavorites(favoriteNewsEntity.apiId)
        } else {
            addToFavorites(favoriteNewsEntity)
        }
    }
}

