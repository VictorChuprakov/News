package com.example.movies.details.data.model

data class NewsId(
    val content: String,
    val createdAt: String,
    val id: Int,
    val image: String,
    val link: String,
    val newsCategoryList: NewsCategoryList,
    val newsParserIdentifier: String,
    val newsSystemIdentifier: String,
    val status: String,
    val title: String
)