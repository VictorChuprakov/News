package com.example.movies.news.data.main.model

data class NewsResponse(
    val lastPage: Int,
    val news: List<NewsItem>,
)