package com.example.core.network

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.core.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit


val networkModule = module {
    factory { provideMoshi() }
    factory { provideHttpCache(app = get()) }
    factory { provideConnectivityManager(app = get()) }
    factory { provideLoggingInterceptor() }
    factory { provideDefaultOkHttpClient(app = get(), cache = get(), loggingInterceptor = get()) }
    single { provideRetrofit(httpClient = get(), moshi = get()) }
}


private const val CONNECT_TIMEOUT_AMOUNT_MINUTES = 1L
private const val READ_TIME_OUT_AMOUNT_SECONDS = 60L
private const val WRITE_TIME_OUT_AMOUNT_SECONDS = 60L
private const val BASE_URL = "https://test.kz/"

private fun provideMoshi(): Moshi {
    return Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .addLast(KotlinJsonAdapterFactory())
        .build()
}

private fun provideHttpCache(app: Application): Cache {
    val cacheSize: Long = 10 * 1024 * 1024
    return Cache(app.cacheDir, cacheSize)
}

private fun provideConnectivityManager(app: Application): ConnectivityManager {
    return app.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}

private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = when(BuildConfig.DEBUG) {
        true -> HttpLoggingInterceptor.Level.BODY
        false -> HttpLoggingInterceptor.Level.NONE
    }
    return loggingInterceptor
}

private fun provideDefaultOkHttpClient(
    app: Application,
    cache: Cache,
    loggingInterceptor: HttpLoggingInterceptor,
): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(ChuckerInterceptor.Builder(app.applicationContext).build())
        .cache(cache)
        .hostnameVerifier { _, _ -> true }
        .connectTimeout(CONNECT_TIMEOUT_AMOUNT_MINUTES, TimeUnit.MINUTES)
        .readTimeout(READ_TIME_OUT_AMOUNT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIME_OUT_AMOUNT_SECONDS, TimeUnit.SECONDS)
        .build()
}

private fun provideRetrofit(httpClient: OkHttpClient, moshi: Moshi): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}

