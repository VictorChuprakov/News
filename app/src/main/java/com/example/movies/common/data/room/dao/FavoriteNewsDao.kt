package com.example.movies.common.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movies.common.data.room.entity.FavoriteNewsEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteNewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteNews(news: FavoriteNewsEntity)

    @Query("DELETE FROM favorite_news WHERE apiId = :apiId")
    suspend fun removeFavoriteNews(apiId: Int)

    @Query("SELECT * FROM favorite_news WHERE apiId = :apiId")
    suspend fun getFavoriteNewsById(apiId: Int): FavoriteNewsEntity?

    @Query("SELECT * FROM favorite_news")
    fun getAllFavoriteNews(): Flow<List<FavoriteNewsEntity>>

    @Query("SELECT * FROM favorite_news WHERE apiId = :apiId")
    fun getNewsById(apiId: Int): FavoriteNewsEntity
}
