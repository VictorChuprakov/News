package com.example.movies.details.data.repository

import com.example.movies.details.data.mapper.toNewsId
import com.example.movies.details.data.model.NewsId
import com.example.movies.details.domain.GetNewsRepositoryById
import com.example.movies.shared.data.api.ApiService
import com.example.movies.shared.until.Result
import java.io.IOException

class GetNewsRepositoryByIdImpl(private val apiService: ApiService) : GetNewsRepositoryById {
    override suspend fun getNewsById(id: Int): Result<NewsId> {
        return try {
            val response = apiService.getNewsId(id)
            if (response.isSuccessful) {
                val newsId = response.body()?.toNewsId()
                if (newsId != null) {
                    Result.Success(newsId)
                } else {
                    Result.Error("Response body is null")
                }
            } else {
                Result.Error("Request failed with code: ${response.code()}")
            }
        } catch (e: IOException) {
            Result.Error("Network error: ${e.localizedMessage}")
        } catch (e: Exception) {
            Result.Error("An unexpected error occurred: ${e.localizedMessage}")
        }
    }
}
