package com.example.movies.common.data.api



sealed class State<out T> {
    data object Idle : State<Nothing>()
    data class Success<T>(val data: T) : State<T>()
    data object Loading : State<Nothing>()
    data class Error(val networkError: NetworkError, val throwable: Throwable) : State<Nothing>()
}

enum class NetworkError {
    UNAUTHORIZED,
    CONFLICT,
    REQUEST_TIMEOUT,
    PAYLOAD_TOO_LARGE,
    SERVER_ERROR,
    DATA_NOT_FOUND,
    NETWORK_UNAVAILABLE,
    UNKNOWN
}


