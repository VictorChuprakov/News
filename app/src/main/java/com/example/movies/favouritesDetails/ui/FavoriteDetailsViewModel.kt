package com.example.movies.favouritesDetails.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.common.data.room.FavoriteNewEntity
import com.example.movies.common.repository.DatabaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteDetailsViewModel(private val databaseRepository: DatabaseRepository) : ViewModel() {
    private val _newsDetails = MutableStateFlow<FavoriteNewEntity?>(null)
    val newsDetails: StateFlow<FavoriteNewEntity?> = _newsDetails

    fun getNewsDetailsById(id: Int) {
        viewModelScope.launch {
            val favoriteNews = databaseRepository.getNewsDetailsById(id)
            _newsDetails.value = favoriteNews
        }
    }

    fun deleteDetails(id: Int) {
        viewModelScope.launch {
            databaseRepository.deleteFavorite(id)
            _newsDetails.value = null
        }
    }
}
