package com.example.movies.favorite.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.common.data.room.entity.FavoriteNewsEntity
import com.example.movies.common.domain.FavoriteNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteNewsViewModel @Inject constructor(
    private val favoriteNewsRepository: FavoriteNewsRepository
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<FavoriteNewsEntity>>(emptyList())
    val favorites = _favorites.asStateFlow()

    init {
        getAllFavorites()
    }

    private fun getAllFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteNewsRepository.getAllFavoriteNews().collect { newsItems ->
                _favorites.value = newsItems
            }
        }
    }
}
