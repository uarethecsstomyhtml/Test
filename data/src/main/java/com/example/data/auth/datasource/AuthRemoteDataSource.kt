package com.example.data.auth.datasource

import com.example.domain.auth.AuthResponse
import com.example.domain.auth.signin.SignInRequest
import com.example.domain.auth.signup.SignUpRequest
import com.example.domain.networkcomponents.NetworkResult

interface AuthRemoteDataSource {

    suspend fun signIn(request: SignInRequest): NetworkResult<AuthResponse>

    suspend fun signUp(request: SignUpRequest): NetworkResult<AuthResponse>


}