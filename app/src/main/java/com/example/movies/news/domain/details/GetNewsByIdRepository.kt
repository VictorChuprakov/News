package com.example.movies.news.domain.details

import com.example.movies.common.data.api.State
import com.example.movies.news.data.details.model.NewsId

interface GetNewsByIdRepository  {
    suspend fun getNewsById(id: Int): State<NewsId>
}
