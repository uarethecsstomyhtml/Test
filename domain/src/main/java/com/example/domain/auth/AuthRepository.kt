package com.example.domain.auth

import com.example.domain.auth.signin.SignInRequest
import com.example.domain.auth.signup.SignUpRequest
import com.example.domain.networkcomponents.NetworkResult

interface AuthRepository {

    suspend fun signIn(request: SignInRequest): NetworkResult<AuthUi>

    suspend fun signUp(request: SignUpRequest): NetworkResult<AuthUi>

}