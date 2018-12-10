package com.dmytro.mvvmtemplate.common.rest

import com.dmytro.mvvmtemplate.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun <T> createService(clazz: Class<T>) = Retrofit.Builder()
        .addCallAdapterFactory(CallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.API_ENDPOINT)
        .client(OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor())
                .build())
        .build()
        .create(clazz)

private fun loggingInterceptor() = HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)