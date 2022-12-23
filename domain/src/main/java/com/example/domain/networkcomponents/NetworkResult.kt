package com.example.domain.networkcomponents

sealed class NetworkResult<T> {

    /**
     * A state that shows the [data] is the last known update.
     */
    data class Success<T>(val data: T) : NetworkResult<T>()

    /**
     * A state to show a [throwable] is thrown beside the [errorMessage] which is cached.
     */
    data class Error<T>(val throwable: NetworkFailure, val baseErrorResponse: BaseErrorResponse? = null, val code: Int = 0) : NetworkResult<T>()

}