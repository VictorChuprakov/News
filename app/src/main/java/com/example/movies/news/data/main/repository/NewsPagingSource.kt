package com.example.movies.news.data.main.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movies.common.data.api.ApiService
import com.example.movies.news.data.main.mapper.toNewsResponse
import com.example.movies.news.data.main.model.NewsItem
import retrofit2.HttpException
import java.io.IOException

class NewsPagingSource(
    private val apiService: ApiService,
    private val category: String
) : PagingSource<Int, NewsItem>() {

    override fun getRefreshKey(state: PagingState<Int, NewsItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsItem> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getNews(page, 20, category)
            if (response.isSuccessful) {
                val croppedNews = response.body()?.toNewsResponse()
                LoadResult.Page(
                    data = croppedNews?.news ?: emptyList(),
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (page < (croppedNews?.lastPage ?: 0)) page + 1 else null
                )
            } else {
                LoadResult.Error(Exception("Error loading data: ${response.message()}"))
            }
        } catch (e: IOException) {
            LoadResult.Error(Exception("Network unavailable", e))
        } catch (e: HttpException) {
            val errorMessage = when (e.code()) {
                401 -> "Unauthorized access"
                403 -> "Forbidden access"
                404 -> "Page not found"
                in 500..599 -> "Server error"
                else -> "HTTP error: ${e.message()}"
            }
            LoadResult.Error(Exception("HTTP error: $errorMessage", e))
        } catch (e: Exception) {
            LoadResult.Error(Exception("Unknown error: ${e.localizedMessage}", e))
        }
    }
}

