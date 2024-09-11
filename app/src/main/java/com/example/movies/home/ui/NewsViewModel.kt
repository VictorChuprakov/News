package com.example.movies.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movies.home.data.News
import com.example.movies.home.domain.GetNewsRepository
import com.example.movies.shared.data.dataprefence.DataPreference
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repository: GetNewsRepository,
    private val dataPreference: DataPreference
) : ViewModel() {

    private val _currentCategory = MutableStateFlow("health")
    val currentCategory = _currentCategory.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val newsPagerFlow: Flow<PagingData<News>> = _currentCategory
        .flatMapLatest { category ->
            repository.getNewsPager(category)
        }
        .cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            _currentCategory.value = dataPreference.categoryState.first()
        }
    }

    fun updateCategory(newCategory: String) {
        viewModelScope.launch {
            if (_currentCategory.value != newCategory) {
                dataPreference.SaveCategoryState(newCategory)
                _currentCategory.value = newCategory
            }
        }
    }
}
