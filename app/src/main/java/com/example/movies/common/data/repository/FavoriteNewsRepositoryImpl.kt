package com.example.movies.common.data.repository

import com.example.movies.common.data.room.dao.FavoriteNewsDao
import com.example.movies.common.data.room.entity.FavoriteNewsEntity
import com.example.movies.common.domain.FavoriteNewsRepository
import kotlinx.coroutines.flow.Flow

class FavoriteNewsRepositoryImpl(private val favoriteNewsDao: FavoriteNewsDao) : FavoriteNewsRepository {

    override suspend fun addFavoriteNews(news: FavoriteNewsEntity) =
        favoriteNewsDao.addFavoriteNews(news)

    override suspend fun removeFavoriteNews(apiId: Int) = favoriteNewsDao.removeFavoriteNews(apiId)

    override suspend fun getFavoriteNewsById(apiId: Int): FavoriteNewsEntity? =
        favoriteNewsDao.getFavoriteNewsById(apiId)

    override suspend fun getNewsById(apiId: Int): FavoriteNewsEntity =
        favoriteNewsDao.getNewsById(apiId)

    override fun getAllFavoriteNews(): Flow<List<FavoriteNewsEntity>> =
        favoriteNewsDao.getAllFavoriteNews()
}

