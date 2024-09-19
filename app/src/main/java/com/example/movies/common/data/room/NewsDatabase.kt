package com.example.movies.common.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(entities = [FavoriteNewEntity::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun favoriteNewsDao(): FavoriteNewsDao

    companion object {
        @Volatile
        private var INSTANCE: NewsDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): NewsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "news_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
