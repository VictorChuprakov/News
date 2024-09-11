package com.example.movies.home.domain

import androidx.paging.PagingData
import com.example.movies.home.data.News
import kotlinx.coroutines.flow.Flow

interface GetNewsRepository {
     fun getNewsPager(category: String) : Flow<PagingData<News>>
}