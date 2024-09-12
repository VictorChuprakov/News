package com.example.movies.news.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movies.news.data.mapper.toCropperNews
import com.example.movies.news.data.model.News
import com.example.movies.shared.data.api.ApiService
import retrofit2.HttpException
import java.io.IOException

class NewsPagingSource(private val apiService: ApiService, private val category: String) : PagingSource<Int, News>() {

    override fun getRefreshKey(state: PagingState<Int, News>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition)
        return anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
        return try {
            val page = params.key ?: 1
            val limit = 20
            val response = apiService.getNews(page, limit, category)

            if (response.isSuccessful) {
                val body = response.body()

                if (body != null) {
                    // Преобразуем DTO в бизнес-модель
                    val croppedNews = body.toCropperNews()

                    LoadResult.Page(
                        data = croppedNews.news,  // Используем новости из преобразованного объекта
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (page < croppedNews.lastPage) page + 1 else null
                    )
                } else {
                    LoadResult.Error(Exception("Пустой ответ"))
                }
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}
