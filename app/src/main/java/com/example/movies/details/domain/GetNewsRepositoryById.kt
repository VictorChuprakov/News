package com.example.movies.details.domain

import com.example.movies.details.data.model.NewsId
import com.example.movies.shared.until.Result

interface GetNewsRepositoryById {
    suspend fun getNewsById(id: Int): Result<NewsId>
}
