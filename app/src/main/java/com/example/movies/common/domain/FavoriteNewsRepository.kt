package com.example.movies.common.domain

import com.example.movies.common.data.room.entity.FavoriteNewsEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteNewsRepository {
    suspend fun addFavoriteNews(news: FavoriteNewsEntity)
    suspend fun removeFavoriteNews(apiId: Int)
    suspend fun getFavoriteNewsById(apiId: Int): FavoriteNewsEntity?
    fun getAllFavoriteNews():  Flow<List<FavoriteNewsEntity>>
    suspend fun getNewsById(apiId: Int): FavoriteNewsEntity
}