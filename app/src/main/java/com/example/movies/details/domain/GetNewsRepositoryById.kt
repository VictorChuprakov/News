package com.example.movies.details.domain

import com.example.movies.details.data.model.NewsId

interface GetNewsRepositoryById {
    suspend fun getNewsById(id: Int): NewsId?
}
