package com.example.movies.news.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movies.common.data.dataprefence.DataPreference
import com.example.movies.news.domain.GetNewsRepository

class NewsViewModelFactory(
    private val repository: GetNewsRepository,
    private val dataPreference: DataPreference,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewsViewModel(repository, dataPreference) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
