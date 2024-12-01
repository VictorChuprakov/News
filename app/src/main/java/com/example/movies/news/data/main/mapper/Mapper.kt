package com.example.movies.news.data.main.mapper

import com.example.movies.news.data.main.model.NewsItem
import com.example.movies.news.data.main.model.NewsResponse
import com.example.movies.news.data.main.modelDTO.NewsItemDTO
import com.example.movies.news.data.main.modelDTO.NewsResponseDTO

fun NewsResponseDTO.toNewsResponse(): NewsResponse {
    return NewsResponse(
        lastPage = this.lastPage ?: 0,
        news = this.news?.map { it.toNews() } ?: emptyList()
        )
}

fun NewsItemDTO.toNews(): NewsItem {
    return NewsItem(
        createdAt = this.createdAt?: "",
        id = this.id ?: 0,
        image = this.image?: "",
        title = this.title?: "",
    )
}
