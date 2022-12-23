package com.example.auth.di

import com.example.data.auth.datasource.AuthRemoteDataSource
import com.example.data.auth.remote.DefaultAuthRemoteDataSource
import org.koin.dsl.module


val authDataSourceModule = module {
    single<AuthRemoteDataSource> { DefaultAuthRemoteDataSource(authApi = get()) }
}