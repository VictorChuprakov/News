package com.example.movies.shared.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_News")
data class FavoriteNewEntity(
    val apiId: Int,
    val title: String,
    val content: String,
    val image: String,
    val createdAt: String,
    val link: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
)
