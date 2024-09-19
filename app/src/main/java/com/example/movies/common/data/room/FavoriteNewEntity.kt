package com.example.movies.common.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_News")
data class FavoriteNewEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val apiId: Int,
    val title: String,
    val content: String,
    val image: String,
)
