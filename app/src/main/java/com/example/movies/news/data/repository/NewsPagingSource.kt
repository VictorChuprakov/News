package com.example.movies.news.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movies.common.data.api.ApiService
import com.example.movies.news.data.mapper.toCropperNews
import com.example.movies.news.data.model.News

class NewsPagingSource(private val apiService: ApiService, private val category: String) : PagingSource<Int, News>() {
    override fun getRefreshKey(state: PagingState<Int, News>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getNews(page, 20, category)

            response.body()?.let { body ->
                val croppedNews = body.toCropperNews()
                LoadResult.Page(
                    data = croppedNews.news,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (page < croppedNews.lastPage) page + 1 else null
                )
            } ?: LoadResult.Error(Exception("Пустой ответ"))
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
