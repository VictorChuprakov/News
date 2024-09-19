package com.example.movies.news.data.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movies.common.data.api.ApiError
import com.example.movies.common.data.api.ApiService
import com.example.movies.news.data.mapper.toCropperNews
import com.example.movies.news.data.model.News
import java.io.IOException

class NewsPagingSource(
    private val apiService: ApiService,
    private val category: String
) : PagingSource<Int, News>() {

    override fun getRefreshKey(state: PagingState<Int, News>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
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
            } ?: LoadResult.Error(
                Exception(ApiError.RESPONSE_NULL.name)
            )
        } catch (e: IOException) {
            LoadResult.Error(
                Exception(ApiError.NETWORK_ERROR.name, e)
            )
        } catch (e: HttpException) {
            LoadResult.Error(
                Exception(ApiError.REQUEST_FAILED.name, e)
            )
        } catch (e: Exception) {
            LoadResult.Error(
                Exception(ApiError.UNEXPECTED_ERROR.name, e)
            )
        }
    }
}


