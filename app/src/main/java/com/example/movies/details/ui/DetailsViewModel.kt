package com.example.movies.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.movies.details.data.model.NewsId
import com.example.movies.details.domain.GetNewsRepositoryById
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class DetailsViewModel(private val repository: GetNewsRepositoryById) : ViewModel() {
    private val _newsById = MutableStateFlow<NewsId?>(null)
    val newsById = _newsById.asStateFlow()

    private val _error = MutableStateFlow("")
    val error = _error.asStateFlow()

    fun getNewsById(id: Int) {
        viewModelScope.launch {
            try {
                _newsById.value = repository.getNewsById(id)
            }
            catch (e: IOException){
                _error.value = "Ошибка сети"
            }
            catch (e: HttpException){
                _error.value = "Ошибка запроса"
            }
        }
    }

    fun cancellationError(){
        _error.value = ""
    }
}
