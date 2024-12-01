package com.example.movies.news.data.main.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movies.common.data.api.ApiService
import com.example.movies.news.data.main.model.NewsItem
import com.example.movies.news.domain.main.GetNewsRepository
import kotlinx.coroutines.flow.Flow

class GetNewsRepositoryImpl(private val apiService: ApiService) : GetNewsRepository {
    override fun getNewsPager(category: String): Flow<PagingData<NewsItem>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false ),
            pagingSourceFactory = { NewsPagingSource(apiService, category) }
        ).flow
    }
}
