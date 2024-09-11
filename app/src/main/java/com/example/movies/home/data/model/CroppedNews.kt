package com.example.movies.home.data


data class CroppedNews(
    val currentPage: Int,
    val lastPage: Int,
    val news: List<News>,
    val perPage: Int,
    val total: Int
)