package com.example.data.auth.remote

import com.example.domain.auth.signin.SignInRequest
import com.example.data.auth.datasource.AuthRemoteDataSource
import com.example.domain.auth.AuthResponse
import com.example.domain.auth.signup.SignUpRequest
import com.example.domain.networkcomponents.NetworkResult

class DefaultAuthRemoteDataSource(
    private val authApi: AuthApi
) : AuthRemoteDataSource {

    override suspend fun signIn(request: SignInRequest): NetworkResult<AuthResponse> {
        // Mock up
        val response = AuthResponse(accessToken = "123", refreshToken = "1234")
        return NetworkResult.Success(response)
//        return handleResponse(BaseErrorResponse::class) { authApi.signIn(request) }
    }

    override suspend fun signUp(request: SignUpRequest): NetworkResult<AuthResponse> {
        val response = AuthResponse(accessToken = "123", refreshToken = "1234")
        return NetworkResult.Success(response)
//        return handleResponse(BaseErrorResponse::class) { authApi.signUp(request) }
    }

}