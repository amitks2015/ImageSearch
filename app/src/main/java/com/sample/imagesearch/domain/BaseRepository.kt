package com.sample.imagesearch.domain

import retrofit2.Response
import java.lang.Exception

abstract class BaseRepository {
    suspend fun<T> safeApiCall(call: suspend () -> Response<T>): NetworkResponse<T> {
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                response.body()?.let {
                    return NetworkResponse.Success<T>(data = it)
                }
            }
            return NetworkResponse.Error(message = response.code().toString())
        } catch (e: Exception) {
            return NetworkResponse.Error(message = e.message ?: "")
        }
    }
}