package com.example.movies.common.data.api

sealed class State<out T> {
    object Loading : State<Nothing>()
    data class Success<T>(val data: T) : State<T>()
    data class Error(val error: ApiError) : State<Nothing>()
}
