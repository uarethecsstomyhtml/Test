package com.example.auth.di

import com.example.data.auth.repository.DefaultAuthRepository
import com.example.domain.auth.AuthRepository
import org.koin.dsl.module

val authRepositoryModule = module {
    single<AuthRepository> {
        DefaultAuthRepository(
            authRemoteDataSource = get(),
            authFacadeMapper = get()
        )
    }
}