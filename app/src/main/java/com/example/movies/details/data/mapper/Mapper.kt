package com.example.movies.details.data.mapper

import com.example.movies.details.data.model.NewsId
import com.example.movies.details.data.model.NewsIdDTO

fun NewsIdDTO.toNewsId(): NewsId {
    return NewsId(
        content = this.content ?: "",
        id = this.id ?: 0,
        image = this.image ?: "",
        title = this.title ?: "",
    )
}
