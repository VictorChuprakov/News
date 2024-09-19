package com.example.movies.common.data.api

import com.example.movies.details.data.model.NewsIdDTO
import com.example.movies.news.data.CroppedNewsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("cropped-news")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("categories[]") category: String
        ) :Response<CroppedNewsDTO>


    @GET("news/{id}")
    suspend fun getNewsId(
        @Path("id") id: Int
    ): Response<NewsIdDTO>
}