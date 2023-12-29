package com.example.data.apimodule.common

import com.example.domain.common.ResultWrapper
import com.example.domain.common.exepctions.ServerError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.util.concurrent.TimeoutException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): Flow<ResultWrapper<T>> = flow {
    emit(ResultWrapper.Loading)
    val result = apiCall.invoke()
    emit(ResultWrapper.Success(result))
}.catch {
    when (it) {
        is HttpException -> emit(ResultWrapper.ServerException(ServerError(it.localizedMessage!!)))
        is TimeoutException -> emit(ResultWrapper.Error(it))
        is Exception -> emit(ResultWrapper.Error(it))
    }
}
