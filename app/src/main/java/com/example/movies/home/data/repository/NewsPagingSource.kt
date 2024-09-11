package com.example.movies.home.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movies.home.data.News
import com.example.movies.shared.data.api.ApiService


class NewsPagingSource(private val repository: ApiService, private val category: String) : PagingSource<Int, News>() {
    override fun getRefreshKey(state: PagingState<Int, News>): Int? {
        return state.anchorPosition
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
        return try {
            val page = params.key ?: 1
            val limit = 20
            val response = repository.getNews(page, limit, category)
            LoadResult.Page(
                data = response.news,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page == response.lastPage) null else page + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
