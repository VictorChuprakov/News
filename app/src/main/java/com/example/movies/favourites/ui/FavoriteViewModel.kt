package com.example.movies.favourites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.common.data.room.FavoriteNewEntity
import com.example.movies.common.domain.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val databaseRepository: DatabaseRepository) : ViewModel() {
     val news: StateFlow<List<FavoriteNewEntity>> = databaseRepository.getAllFavorites()
          .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
}
