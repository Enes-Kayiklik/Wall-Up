package com.eneskayiklik.wallup.utils.network

sealed class Resource<T>(
    val response: T? = null,
    val errorMessage: String? = null
) {
    data class Success<T>(val data: T) : Resource<T>(data)
    data class Error<T>(val message: String, val data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}
