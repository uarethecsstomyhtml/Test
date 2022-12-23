package com.example.auth.di

import com.example.data.auth.mapper.AuthFacadeMapper
import com.example.data.auth.mapper.AuthMapper
import org.koin.dsl.module


val authMapperModule = module {
    single { AuthFacadeMapper(loginMapper = get()) }
    single { AuthMapper() }
}