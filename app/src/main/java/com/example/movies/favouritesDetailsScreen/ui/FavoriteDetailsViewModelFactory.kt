package com.example.movies.favourites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movies.favouritesDetailsScreen.ui.FavoriteDetailsViewModel
import com.example.movies.shared.domain.DatabaseRepository

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