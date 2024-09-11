package com.example.movies.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movies.home.data.News
import com.example.movies.home.domain.GetNewsRepository
import com.example.movies.shared.data.api.ApiService
import kotlinx.coroutines.flow.Flow

class GetNewsRepositoryImpl(private val apiService: ApiService) : GetNewsRepository {
    override fun getNewsPager(category: String): Flow<PagingData<News>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false ),
            pagingSourceFactory = { NewsPagingSource(apiService, category)}
        ).flow
    }
}
