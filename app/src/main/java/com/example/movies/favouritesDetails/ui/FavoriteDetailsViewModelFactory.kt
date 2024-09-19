package com.example.movies.favourites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movies.common.repository.DatabaseRepository
import com.example.movies.favouritesDetails.ui.FavoriteDetailsViewModel

class FavoriteDetailsViewModelFactory(
    private val databaseRepository: DatabaseRepository

) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteDetailsViewModel(databaseRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}