package com.example.movies.news.data.main.modelDTO

data class NewsResponseDTO(
    val lastPage: Int? = null,
    val news: List<NewsItemDTO>? = null,
)