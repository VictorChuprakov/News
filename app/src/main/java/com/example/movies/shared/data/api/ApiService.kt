package com.example.movies.shared.data.api

import com.example.movies.details.data.model.NewsId
import com.example.movies.home.data.CroppedNews
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
        ) :CroppedNews


    @GET("news/{id}")
    suspend fun getNewsId(
        @Path("id") id: Int
    ): Response<NewsId>
}