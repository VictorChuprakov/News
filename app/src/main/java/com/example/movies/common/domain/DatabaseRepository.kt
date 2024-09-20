package com.example.movies.common.domain

import com.example.movies.common.data.room.FavoriteNewEntity
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    suspend fun insert(news: FavoriteNewEntity)
    suspend fun delete(apiId: Int)
    suspend fun deleteFavorite(id: Int)
    suspend fun isFavorite(apiId: Int): Boolean
    suspend fun getNewsDetailsById(id: Int): FavoriteNewEntity
    fun getAllFavorites(): Flow<List<FavoriteNewEntity>>  // Добавьте этот метод
}
