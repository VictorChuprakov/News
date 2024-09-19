package com.example.movies.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movies.common.repository.DatabaseRepository
import com.example.movies.details.domain.GetNewsRepositoryById

class DetailsViewModelFactory(
    private val repository: GetNewsRepositoryById,
    private val databaseRepository: DatabaseRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailsViewModel(repository, databaseRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
