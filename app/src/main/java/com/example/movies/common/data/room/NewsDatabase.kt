package com.example.movies.common.data.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [FavoriteNewEntity::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun favoriteNewsDao(): FavoriteNewsDao

}
