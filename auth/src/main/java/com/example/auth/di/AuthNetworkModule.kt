package com.example.auth.di

import com.example.data.auth.remote.AuthApi
import org.koin.dsl.module
import retrofit2.Retrofit


val authNetworkModule = module {
    single { provideAuthApi(retrofit = get()) }
}

fun provideAuthApi(retrofit: Retrofit): AuthApi {
    return retrofit.create(AuthApi::class.java)
}