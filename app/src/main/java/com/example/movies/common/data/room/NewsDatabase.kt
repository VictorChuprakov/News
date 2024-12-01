package com.example.movies.common.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movies.common.data.room.dao.FavoriteNewsDao
import com.example.movies.common.data.room.entity.FavoriteNewsEntity


@Database(entities = [FavoriteNewsEntity::class], version = 3)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun favoriteNewsDao(): FavoriteNewsDao

}
