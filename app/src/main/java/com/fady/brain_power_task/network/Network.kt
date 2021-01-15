package com.fady.brain_power_task.network

import com.fady.brain_power_task.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val sLogLevel = if (BuildConfig.DEBUG)
    HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

fun getRetrofitClient(baseUrl: String) = retrofitClient(baseUrl, okHttpClient(false))
private fun getLogInterceptor() = HttpLoggingInterceptor().apply { level = sLogLevel }

private fun okHttpClient(addAuthHeader: Boolean) = OkHttpClient.Builder()
    .addInterceptor(getLogInterceptor())
    .apply { setTimeOutToOkHttpClient(this) }
    .build()

private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

private fun setTimeOutToOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder) =
    okHttpClientBuilder.apply {
        readTimeout(30L, TimeUnit.SECONDS)
        connectTimeout(30L, TimeUnit.SECONDS)
        writeTimeout(30L, TimeUnit.SECONDS)
    }