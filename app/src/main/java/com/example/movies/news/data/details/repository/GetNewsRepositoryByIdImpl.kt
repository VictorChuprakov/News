package com.example.movies.news.data.details.repository

import com.example.movies.common.data.api.ApiService
import com.example.movies.common.data.api.NetworkError
import com.example.movies.common.data.api.State
import com.example.movies.news.data.details.mapper.toNewsId
import com.example.movies.news.data.details.model.NewsId
import com.example.movies.news.domain.details.GetNewsByIdRepository
import java.io.IOException

class GetNewsByIdRepositoryImpl(private val apiService: ApiService) : GetNewsByIdRepository {

    override suspend fun getNewsById(id: Int): State<NewsId> {
        return try {
            val response = apiService.getNewsId(id)
            when (response.code()) {
                in 200..299 -> {
                    response.body()?.toNewsId()?.let {
                        State.Success(it)
                    } ?: State.Error(NetworkError.DATA_NOT_FOUND, Exception("News data is missing"))
                }
                401 -> State.Error(NetworkError.UNAUTHORIZED, Throwable("Access denied: Unauthorized"))
                409 -> State.Error(NetworkError.CONFLICT, Throwable("Request conflict detected"))
                404 -> State.Error(NetworkError.DATA_NOT_FOUND, Throwable("The requested news was not found"))
                408 -> State.Error(NetworkError.REQUEST_TIMEOUT, Throwable("The request timed out, please try again"))
                413 -> State.Error(NetworkError.PAYLOAD_TOO_LARGE, Throwable("The data size exceeds the allowed limit"))
                in 500..599 -> State.Error(NetworkError.SERVER_ERROR, Throwable("Server encountered an error, please try again later"))
                else -> State.Error(NetworkError.UNKNOWN, Throwable("An unexpected error occurred: code ${response.code()}"))
            }
        } catch (e: IOException) {
            State.Error(NetworkError.NETWORK_UNAVAILABLE, Throwable("No internet connection, please check your network", e))
        } catch (e: Exception) {
            State.Error(NetworkError.UNKNOWN, Throwable("An unexpected error occurred", e))
        }
    }
}
