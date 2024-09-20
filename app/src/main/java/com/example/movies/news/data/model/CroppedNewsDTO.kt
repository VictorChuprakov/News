package com.example.movies.news.data.model

import com.example.movies.news.data.NewsDTO

data class CroppedNewsDTO(
    val currentPage: Int? = null,
    val lastPage: Int? = null,
    val news: List<NewsDTO>? = null,
    val perPage: Int? = null,
    val total: Int? = null
)