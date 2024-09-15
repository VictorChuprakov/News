package com.example.movies.shared.domain

import com.example.movies.shared.data.room.FavoriteNewEntity
import com.example.movies.shared.data.room.FavoriteNewsDao
import kotlinx.coroutines.flow.Flow

class DatabaseRepository(private val favoriteNewsDao: FavoriteNewsDao) {

    val allFavorites: Flow<List<FavoriteNewEntity>> = favoriteNewsDao.getAllFavorite()

    suspend fun insert(news: FavoriteNewEntity) {
        favoriteNewsDao.insertFavorite(news)
    }

    suspend fun delete(id: Int) {
        favoriteNewsDao.deleteByApiId(id)
    }

    suspend fun deleteFavorite(id: Int){
        favoriteNewsDao.deleteNewsById(id)
    }
    suspend fun isFavorite(apiId: Int): Boolean {
        return favoriteNewsDao.getFavoriteByApiId(apiId) != null
    }

    suspend fun getNewsDetailsById(id: Int): FavoriteNewEntity {
        return favoriteNewsDao.getNewsById(id)

    }
}