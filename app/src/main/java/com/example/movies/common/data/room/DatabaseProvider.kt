package com.example.movies.common.data.room

import android.content.Context
import com.example.movies.common.domain.DatabaseRepositoryImpl
import com.example.movies.common.repository.DatabaseRepository

object DatabaseProvider {
    private lateinit var database: NewsDatabase

    fun init(context: Context) {
        database = NewsDatabase.getDatabase(context)
    }

    val databaseRepository: DatabaseRepository by lazy {
        DatabaseRepositoryImpl(database.favoriteNewsDao())
    }
}