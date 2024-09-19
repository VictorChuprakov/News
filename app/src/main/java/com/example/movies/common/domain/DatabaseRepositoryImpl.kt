package com.example.movies.common.domain

import com.example.movies.common.data.room.FavoriteNewEntity
import com.example.movies.common.data.room.FavoriteNewsDao
import com.example.movies.common.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow

class DatabaseRepositoryImpl(private val favoriteNewsDao: FavoriteNewsDao): DatabaseRepository {

    override suspend fun insert(news: FavoriteNewEntity) {
        favoriteNewsDao.insertFavorite(news)
    }

    override suspend fun delete(apiId: Int) {
        favoriteNewsDao.deleteByApiId(apiId)
    }

    override suspend fun deleteFavorite(id: Int) {
        favoriteNewsDao.deleteNewsById(id)
    }

    override suspend fun isFavorite(apiId: Int): Boolean {
        return favoriteNewsDao.getFavoriteByApiId(apiId) != null
    }

    override suspend fun getNewsDetailsById(id: Int): FavoriteNewEntity {
        return favoriteNewsDao.getNewsById(id)
    }

    override fun getAllFavorites(): Flow<List<FavoriteNewEntity>> {
        return favoriteNewsDao.getAllFavorite()
    }

}

