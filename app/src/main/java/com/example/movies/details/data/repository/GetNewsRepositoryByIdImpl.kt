package com.example.movies.details.data.repository

import com.example.movies.common.data.api.ApiError
import com.example.movies.common.data.api.ApiService
import com.example.movies.common.data.api.State
import com.example.movies.details.data.mapper.toNewsId
import com.example.movies.details.data.model.NewsId
import com.example.movies.details.domain.GetNewsRepositoryById
import java.io.IOException

class GetNewsRepositoryByIdImpl(private val apiService: ApiService) : GetNewsRepositoryById {
    override suspend fun getNewsById(id: Int): State<NewsId> {
        return try {
            val response = apiService.getNewsId(id)
            if (response.isSuccessful) {
                val newsId = response.body()?.toNewsId()
                if (newsId != null) {
                    State.Success(newsId)
                } else {
                    State.Error(ApiError.RESPONSE_NULL)
                }
            } else {
                State.Error(ApiError.REQUEST_FAILED)
            }
        } catch (e: IOException) {
            State.Error(ApiError.NETWORK_ERROR)
        } catch (e: Exception) {
            State.Error(ApiError.UNEXPECTED_ERROR)
        }
    }
}
