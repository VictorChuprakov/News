package com.example.movies.common.data.api

import com.example.movies.news.data.details.model.NewsIdDTO
import com.example.movies.news.data.main.modelDTO.NewsResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("cropped-news")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("categories[]") category: String,
        ) :Response<NewsResponseDTO>

    @GET("news/{id}")
    suspend fun getNewsId(
        @Path("id") id: Int
    ): Response<NewsIdDTO>
}