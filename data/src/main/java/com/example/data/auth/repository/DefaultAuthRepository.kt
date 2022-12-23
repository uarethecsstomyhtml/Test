package com.example.data.auth.repository

import com.example.domain.auth.AuthRepository
import com.example.domain.auth.signin.SignInRequest
import com.example.domain.auth.signup.SignUpRequest
import com.example.data.auth.datasource.AuthRemoteDataSource
import com.example.domain.networkcomponents.NetworkResult
import com.example.data.auth.mapper.AuthFacadeMapper
import com.example.domain.auth.AuthUi

class DefaultAuthRepository(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authFacadeMapper: AuthFacadeMapper
) : AuthRepository {

    override suspend fun signIn(request: SignInRequest): NetworkResult<AuthUi> {
        return when(val response = authRemoteDataSource.signIn(request = request)) {
            is NetworkResult.Success -> NetworkResult.Success(authFacadeMapper.loginMapper.map(response.data))
            is NetworkResult.Error -> NetworkResult.Error(response.throwable)
        }
    }

    override suspend fun signUp(request: SignUpRequest): NetworkResult<AuthUi> {
        return when(val response = authRemoteDataSource.signUp(request = request)) {
            is NetworkResult.Success -> NetworkResult.Success(authFacadeMapper.loginMapper.map(response.data))
            is NetworkResult.Error -> NetworkResult.Error(response.throwable)
        }
    }

}