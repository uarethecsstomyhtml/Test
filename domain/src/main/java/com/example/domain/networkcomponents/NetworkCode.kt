package com.example.domain.networkcomponents

const val RESPONSE_SUCCESS_CODE = 200
const val RESPONSE_SUCCESS_CREATE_CODE = 201
const val RESPONSE_SUCCESS_NO_CONTENT = 204
const val RESPONSE_BAD_REQUEST_CODE = 400
const val RESPONSE_UNAUTHORIZED_CODE = 401
const val RESPONSE_NOT_FOUND_PAGE_CODE = 404
const val RESPONSE_METHOD_NOT_ALLOWED_CODE = 405
const val RESPONSE_CONFLICT_CODE = 409
const val RESPONSE_GONE = 410
const val RESPONSE_PRECONDITION_FAILED = 412
const val RESPONSE_AUTH_TIMEOUT = 419
const val RESPONSE_UNPROCESSABLE_ENTITY = 422
const val RESPONSE_TOO_MANY_REQUESTS = 429
const val RESPONSE_SERVER_INTERNAL_ERROR_CODE = 500
const val RESPONSE_GATEWAY_ERROR_CODE = 502

fun Int.isSuccess(): Boolean {
    return when(this) {
        RESPONSE_SUCCESS_CODE,
        RESPONSE_SUCCESS_CREATE_CODE,
        RESPONSE_SUCCESS_NO_CONTENT -> true
        else -> false
    }
}

fun Int.isBadRequest(): Boolean {
    return this == RESPONSE_BAD_REQUEST_CODE
}

fun Int.isUnauthorized(): Boolean {
    return this == RESPONSE_UNAUTHORIZED_CODE
}

fun Int.isNotFoundPage(): Boolean {
    return this == RESPONSE_NOT_FOUND_PAGE_CODE
}

fun Int.isMethodNotAllowed(): Boolean {
    return this == RESPONSE_METHOD_NOT_ALLOWED_CODE
}

fun Int.isConflict(): Boolean {
    return this == RESPONSE_CONFLICT_CODE
}

fun Int.isResponseGone(): Boolean {
    return this == RESPONSE_GONE
}

fun Int.isPreconditionFailed(): Boolean {
    return this == RESPONSE_PRECONDITION_FAILED
}

fun Int.isAuthTimeout(): Boolean {
    return this == RESPONSE_AUTH_TIMEOUT
}

fun Int.isUnprocessableEntity(): Boolean {
    return this == RESPONSE_UNPROCESSABLE_ENTITY
}

fun Int.tooManyRequests(): Boolean {
    return this == RESPONSE_TOO_MANY_REQUESTS
}

fun Int.isServerInternalError(): Boolean {
    return this == RESPONSE_SERVER_INTERNAL_ERROR_CODE
}
fun Int.isBadGateway(): Boolean {
    return this == RESPONSE_GATEWAY_ERROR_CODE
}