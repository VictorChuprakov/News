package com.example.movies.details.domain

import com.example.movies.details.data.model.NewsId
import com.example.movies.common.data.api.State

interface GetNewsRepositoryById {
    suspend fun getNewsById(id: Int): State<NewsId>
}
