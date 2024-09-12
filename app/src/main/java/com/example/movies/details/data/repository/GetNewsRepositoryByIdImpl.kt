package com.example.movies.details.data.repository

import android.util.Log
import com.example.movies.details.data.mapper.toNewsId
import com.example.movies.details.data.model.NewsId
import com.example.movies.details.domain.GetNewsRepositoryById
import com.example.movies.shared.data.api.ApiService
import retrofit2.HttpException
import java.io.IOException

// GetNewsRepositoryByIdImpl.kt
class GetNewsRepositoryByIdImpl(private val apiService: ApiService) : GetNewsRepositoryById {
    override suspend fun getNewsById(id: Int): NewsId? {
        return try {
            val response = apiService.getNewsId(id)
            if (response.isSuccessful) {
                response.body()?.toNewsId()
            } else {
                Log.e("GetNewsRepositoryByIdImpl", "Error response code: ${response.code()}")
                null
            }
        } catch (e: IOException) {
            Log.e("GetNewsRepositoryByIdImpl", "Network error", e)
            null
        } catch (e: HttpException) {
            Log.e("GetNewsRepositoryByIdImpl", "HTTP error", e)
            null
        } catch (e: Exception) {
            Log.e("GetNewsRepositoryByIdImpl", "Unknown error", e)
            null
        }
    }
}