package com.example.movies.news.data.details.mapper

import com.example.movies.news.data.details.model.NewsId
import com.example.movies.news.data.details.model.NewsIdDTO

fun NewsIdDTO.toNewsId(): NewsId {
    return NewsId(
        createdAt = this.createdAt ?: "",
        content = this.content ?: "",
        id = this.id ?: 0,
        image = this.image ?: "",
        title = this.title ?: "",
    )
}
