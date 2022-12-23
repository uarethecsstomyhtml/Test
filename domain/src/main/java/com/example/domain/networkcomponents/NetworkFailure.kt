package com.example.domain.networkcomponents



sealed class NetworkFailure : Throwable() {

    object Unauthorized: NetworkFailure()

    object BadRequest: NetworkFailure()

    object NotFound: NetworkFailure()

    object MethodNotAllowed: NetworkFailure()

    object Conflict: NetworkFailure()

    object Gone: NetworkFailure()

    object PreconditionFailed: NetworkFailure()

    object AuthTimeout: NetworkFailure()

    object UnprocessableEntity: NetworkFailure()

    object TooManyRequests: NetworkFailure()

    object ServerInternal: NetworkFailure()

    object BadGateway: NetworkFailure()

    object Unknown: NetworkFailure()

    object NoConnection: NetworkFailure()
}

//object NetworkException: IOException()