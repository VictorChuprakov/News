package com.example.movies.news.data.mapper

import com.example.movies.news.data.NewsDTO
import com.example.movies.news.data.model.CroppedNews
import com.example.movies.news.data.model.CroppedNewsDTO
import com.example.movies.news.data.model.News

fun CroppedNewsDTO.toCropperNews(): CroppedNews {
    return CroppedNews(
        currentPage = this.currentPage ?: 0,
        lastPage = this.lastPage ?: 0,
        news = this.news?.map { it.toNews() } ?: emptyList(),
        perPage = this.perPage ?: 0,
        total = this.total ?: 0
    )
}

fun NewsDTO.toNews(): News {
    return News(
        categories = this.categories ?: emptyList(),
        id = this.id ?: 0,
        image = this.image ?: "No image available",
        title = this.title ?: "No title available"
    )
}
