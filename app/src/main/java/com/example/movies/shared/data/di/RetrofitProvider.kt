package com.example.movies.shared.data.di

import android.content.Context
import com.example.movies.details.data.repository.GetNewsRepositoryByIdImpl
import com.example.movies.details.domain.GetNewsRepositoryById
import com.example.movies.shared.data.room.NewsDatabase
import com.example.movies.shared.domain.DatabaseRepository
import com.example.movies.news.data.repository.GetNewsRepositoryImpl
import com.example.movies.news.domain.GetNewsRepository
import com.example.movies.shared.data.api.RetrofitClient

object RetrofitProvider {
    private lateinit var database: NewsDatabase

    fun init(context: Context) {
        database = NewsDatabase.getDatabase(context)
    }

    val newsRepository: GetNewsRepository by lazy {
        GetNewsRepositoryImpl(RetrofitClient.apiNews)
    }
    val newsIdRepository: GetNewsRepositoryById by lazy {
        GetNewsRepositoryByIdImpl(RetrofitClient.apiNews)
    }

    val databaseRepository: DatabaseRepository by lazy {
        DatabaseRepository(database.favoriteNewsDao())
    }
}
