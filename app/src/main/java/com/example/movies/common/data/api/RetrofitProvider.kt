package com.example.movies.common.data.api

import com.example.movies.details.data.repository.GetNewsRepositoryByIdImpl
import com.example.movies.details.domain.GetNewsRepositoryById
import com.example.movies.news.data.repository.GetNewsRepositoryImpl
import com.example.movies.news.domain.GetNewsRepository

object RetrofitProvider {
    val newsRepository: GetNewsRepository by lazy {
        GetNewsRepositoryImpl(RetrofitClient.apiNews)
    }
    val newsIdRepository: GetNewsRepositoryById by lazy {
        GetNewsRepositoryByIdImpl(RetrofitClient.apiNews)
    }
}
