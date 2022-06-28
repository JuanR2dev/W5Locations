package com.syllabus.w5.di

import com.squareup.moshi.Moshi
import com.syllabus.w5.BuildConfig
import com.syllabus.w5.repository.remote.dominos.server.DominosRetrofitService
import com.syllabus.w5.repository.remote.dominos.server.DominosService
import com.syllabus.w5.repository.remote.dominos.server.DominosServiceImpl
import com.syllabus.w5.repository.remote.dominos.server.DominosServiceManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = ""

private val logLevel =
    if (!BuildConfig.DEBUG) HttpLoggingInterceptor.Level.NONE
    else HttpLoggingInterceptor.Level.BODY

private fun createOkHttpClient() = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply { level = logLevel })
    .readTimeout(120, TimeUnit.SECONDS)
    .build()

inline fun <reified T> createRetrofitService(okHttpClient: OkHttpClient, url: String): T =
    Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
        .build()
        .create(T::class.java)

val apiModule = module {
    single { createOkHttpClient() }
}

val dominosModule = module {
    single<DominosRetrofitService> { createRetrofitService(get(), BASE_URL) }
    single<DominosService> { DominosServiceImpl(get()) }
    single { DominosServiceManager(get()) }
}