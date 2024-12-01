package com.example.movies.news.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.movies.common.data.dataprefence.DataPreference
import com.example.movies.news.data.main.model.NewsItem
import com.example.movies.news.domain.main.GetNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsHomeViewModel @Inject constructor(
    private val repository: GetNewsRepository,
    private val dataPreference: DataPreference
) : ViewModel() {

    private val _currentCategory = MutableStateFlow("")
    val currentCategory = _currentCategory.asStateFlow()

    // Используем flatMapLatest для того чтобы поток обрабатывал только последние изменения категории
    @OptIn(ExperimentalCoroutinesApi::class)
    val newsFlow: Flow<PagingData<NewsItem>> = currentCategory
        .flatMapLatest { category ->  // Отменяет предыдущий запрос, если приходит новая категория
            repository.getNewsPager(category)
        }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val savedCategory = dataPreference.categoryState.first()
            Log.d("HomeViewModel", "Saved category: $savedCategory")
            _currentCategory.value = savedCategory
        }
    }

    fun saveCategory(newCategory: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_currentCategory.value != newCategory) {
                Log.d("HomeViewModel", "Category changed from ${_currentCategory.value} to $newCategory")
                dataPreference.saveCategoryState(newCategory)
                _currentCategory.value = newCategory
            }
        }
    }
}

