package com.example.movies.details.data.repository

import com.example.movies.details.data.model.NewsId
import com.example.movies.details.domain.GetNewsRepositoryById
import com.example.movies.shared.data.api.ApiService

class GetNewsRepositoryByIdImpl(private val apiService: ApiService): GetNewsRepositoryById {
    override suspend fun getNewsById(id: Int): NewsId? {
        return try {
            val response = apiService.getNewsId(id)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}
