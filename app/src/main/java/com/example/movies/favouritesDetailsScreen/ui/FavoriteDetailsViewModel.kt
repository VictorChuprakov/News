package com.example.movies.favouritesDetailsScreen.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.shared.data.room.FavoriteNewEntity
import com.example.movies.shared.domain.DatabaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteDetailsViewModel(private val databaseRepository: DatabaseRepository) : ViewModel() {
     private val _newsDetails = MutableStateFlow<FavoriteNewEntity?>(null)
     val newsDetails: StateFlow<FavoriteNewEntity?> = _newsDetails

     // Получение новости по ID
     fun getNewsDetailsById(id: Int) {
          viewModelScope.launch {
               try {
                    val favoriteNews = databaseRepository.getNewsDetailsById(id)
                    _newsDetails.value = favoriteNews
               } catch (e: Exception) {
                    _newsDetails.value = null
               }
          }
     }

     // Удаление новости по ID
     fun deleteDetails(id: Int) {
          viewModelScope.launch {
               try {
                    databaseRepository.deleteFavorite(id)
                    _newsDetails.value = null // Обновляем состояние после удаления
               } catch (e: Exception) {
                    // Обработка ошибки удаления
               }
          }
     }
}
