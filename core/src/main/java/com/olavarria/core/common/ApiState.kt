package com.olavarria.core.common

sealed class ApiState<out T> {
    class Success<T>(val data: T) : ApiState<T>()
    class Error(val exception: Throwable) : ApiState<Nothing>()
    class ErrorCode(val message: String) : ApiState<Nothing>()
    object Loading : ApiState<Nothing>()
}

inline fun <T, R> ApiState<T>.map(transform: (T) -> R): ApiState<R> {
    return when (this) {
        is ApiState.Success -> ApiState.Success(transform(data))
        is ApiState.Error -> ApiState.Error(exception)
        is ApiState.Loading -> ApiState.Loading
        is ApiState.ErrorCode -> ApiState.ErrorCode(message)
    }
}