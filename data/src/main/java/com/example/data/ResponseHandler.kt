package com.example.data

import com.example.domain.networkcomponents.*
import retrofit2.Response
import java.io.IOException
import kotlin.reflect.KClass

inline fun <I, reified C: BaseErrorResponse> handleResponse(clazz: KClass<C>, request: () -> Response<I>): NetworkResult<I> {
    return try {
        val response = request()
        val code = response.code()
        when {
            code.isSuccess() -> NetworkResult.Success(response.body() ?: @Suppress("UNCHECKED_CAST") (Unit as I))
            code.isBadRequest() -> NetworkResult.Error(NetworkFailure.BadRequest)
            code.isUnauthorized() -> NetworkResult.Error(NetworkFailure.Unauthorized)
            code.isNotFoundPage() -> NetworkResult.Error(NetworkFailure.NotFound)
            code.isConflict() -> NetworkResult.Error(NetworkFailure.Conflict)
            code.isResponseGone() -> NetworkResult.Error(NetworkFailure.Gone)
            code.isPreconditionFailed() -> NetworkResult.Error(NetworkFailure.PreconditionFailed)
            code.isAuthTimeout() -> NetworkResult.Error(NetworkFailure.AuthTimeout)
            code.isMethodNotAllowed() -> NetworkResult.Error(NetworkFailure.MethodNotAllowed)
            code.isUnprocessableEntity() -> NetworkResult.Error(NetworkFailure.UnprocessableEntity)
            code.tooManyRequests() -> NetworkResult.Error(NetworkFailure.TooManyRequests)
            code.isServerInternalError() -> NetworkResult.Error(NetworkFailure.ServerInternal)
            code.isBadGateway() -> NetworkResult.Error(NetworkFailure.BadGateway)
            else -> NetworkResult.Error(NetworkFailure.Unknown)
        }
    } catch (exc: Exception) {
        exc.printStackTrace()
        when(exc) {
            is IOException -> NetworkResult.Error(NetworkFailure.NoConnection)
            else -> NetworkResult.Error(NetworkFailure.Unknown)
        }
    }
}