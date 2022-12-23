package com.example.experience

import android.app.Application
import com.example.auth.di.*
import com.example.core.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class ExperienceApp : Application() {

    private val authModules = listOf(
        authDataSourceModule,
        authMapperModule,
        authNetworkModule,
        authRepositoryModule,
        authViewModelModule
    )

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@ExperienceApp)
            modules(authModules)
            modules(networkModule)
        }
    }
}