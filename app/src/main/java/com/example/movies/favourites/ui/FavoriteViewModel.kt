package com.example.movies.favourites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.shared.domain.DatabaseRepository
import com.example.movies.shared.data.room.FavoriteNewEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class FavoriteViewModel(private val databaseRepository: DatabaseRepository) : ViewModel() {
     val news: StateFlow<List<FavoriteNewEntity>> = databaseRepository.allFavorites
          .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
}
