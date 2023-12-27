package com.example.domain.common

import com.example.domain.common.exepctions.ServerError

sealed class ResultWrapper<out T> {
    data class Success<out T>(val data: T) : ResultWrapper<T>()
    data object Loading : ResultWrapper<Nothing>()
    data class ServerException(val serverError: ServerError) : ResultWrapper<Nothing>()
    data class Error(var error: Exception) : ResultWrapper<Nothing>()
}