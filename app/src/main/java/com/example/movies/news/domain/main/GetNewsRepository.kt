package com.example.movies.news.domain.main

import androidx.paging.PagingData
import com.example.movies.news.data.main.model.NewsItem
import kotlinx.coroutines.flow.Flow

interface GetNewsRepository {
    fun getNewsPager(category: String): Flow<PagingData<NewsItem>>
}