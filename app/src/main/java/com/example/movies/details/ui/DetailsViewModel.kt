package com.example.movies.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.movies.details.data.model.NewsId
import com.example.movies.details.domain.GetNewsRepositoryById
import com.example.movies.shared.data.room.FavoriteNewEntity
import com.example.movies.shared.domain.DatabaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class DetailsViewModel(
    private val repository: GetNewsRepositoryById,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    private val _newsById = MutableStateFlow<NewsId?>(null)
    val newsById = _newsById.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> get() = _isFavorite

    fun getNewsById(id: Int) {
        viewModelScope.launch {
            try {
                val newsId = repository.getNewsById(id)
                _newsById.value = newsId
                if (newsId != null) {
                    checkIfFavorite(newsId)
                }
            } catch (e: IOException) {
                _error.value = "Ошибка сети"
            } catch (e: HttpException) {
                _error.value = "Ошибка запроса"
            } catch (e: Exception) {
                _error.value = "Произошла ошибка"
            }
        }
    }


    private suspend fun checkIfFavorite(newsId: NewsId) {
        // Проверьте, существует ли запись с указанным apiId
        _isFavorite.value = databaseRepository.isFavorite(newsId.id)
    }


    fun toggleFavorite(news: NewsId?) {
        viewModelScope.launch {
            news?.let {
                val isCurrentlyFavorite = _isFavorite.value
                if (isCurrentlyFavorite) {
                    // Если новость уже избранная, удаляем ее из базы данных
                    databaseRepository.delete(
                        it.id
                    )
                } else {
                    // Если новость не избранная, добавляем ее в базу данных
                    databaseRepository.insert(
                        FavoriteNewEntity(
                            id = 0, // ID будет сгенерирован автоматически
                            apiId = it.id,
                            title = it.title,
                            content = it.content,
                            image = it.image,
                            createdAt = it.createdAt,
                            link = it.link
                        )
                    )
                }
                // Обновляем состояние избранного после успешного добавления или удаления
                _isFavorite.value = !isCurrentlyFavorite
            }
        }
    }


    fun clearError() {
        _error.value = null
    }
}
