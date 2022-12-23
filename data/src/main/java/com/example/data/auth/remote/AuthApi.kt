package com.example.data.auth.remote

import com.example.domain.auth.signin.SignInRequest
import com.example.domain.auth.AuthResponse
import com.example.domain.auth.signup.SignUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


private const val AUTH_API = "auth"

interface AuthApi {

    @POST("$AUTH_API/jwt/create")
    suspend fun signIn(@Body body: SignInRequest): Response<AuthResponse>

    @POST("$AUTH_API/users/set_password/")
    suspend fun signUp(@Body body: SignUpRequest): Response<AuthResponse>
}
