package com.sample.imagesearch.domain

sealed class NetworkResponse<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): NetworkResponse<T>(data)
    class Error<T>(message: String): NetworkResponse<T>(data = null, message = message)
    class Loading<T>: NetworkResponse<T>()
}