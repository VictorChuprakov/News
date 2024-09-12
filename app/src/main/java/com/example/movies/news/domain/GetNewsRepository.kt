package com.example.movies.news.domain

import androidx.paging.PagingData
import com.example.movies.news.data.NewsDTO
import com.example.movies.news.data.model.News
import kotlinx.coroutines.flow.Flow

interface GetNewsRepository {
     fun getNewsPager(category: String) : Flow<PagingData<News>>
}