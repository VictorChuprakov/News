package com.example.movies.common.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteNewsDao {
    @Insert
    suspend fun insertFavorite(news: FavoriteNewEntity)

    @Query("SELECT * FROM favorite_News")
    fun getAllFavorite(): Flow<List<FavoriteNewEntity>>

    @Query("DELETE FROM favorite_news WHERE apiId = :apiId")
    suspend fun deleteByApiId(apiId: Int)

    @Query("DELETE FROM favorite_news WHERE id = :id")
    suspend fun deleteNewsById(id: Int)

    @Query("SELECT * FROM favorite_News WHERE apiId = :apiId")
    suspend fun getFavoriteByApiId(apiId: Int): FavoriteNewEntity?

    @Query("SELECT * FROM favorite_News WHERE id = :id")
    suspend fun getNewsById(id: Int): FavoriteNewEntity
}