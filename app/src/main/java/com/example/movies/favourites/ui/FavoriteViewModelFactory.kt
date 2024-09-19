package com.example.movies.favourites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movies.common.repository.DatabaseRepository

class FavoriteViewModelFactory(
    private val databaseRepository: DatabaseRepository

) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteViewModel(databaseRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}