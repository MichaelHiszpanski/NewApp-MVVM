package com.example.newappversiontwo.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
//import com.example.newappversiontwo.BuildConfig

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


object Api {
    const val API_KEY="d3562306af44495d99f915fa7817657d"
    private val BASE_URL = "https://newsapi.org/v2/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val logging = HttpLoggingInterceptor().apply {
       // level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }


    val httpClient=OkHttpClient.Builder().apply {
        addInterceptor(
            Interceptor {
                chain ->
                val builder=chain.request().newBuilder()
                builder.header("X-Api-key", API_KEY)
                return@Interceptor chain.proceed(builder.build())
            }
        )
        logging.level=HttpLoggingInterceptor.Level.BODY
        addNetworkInterceptor(logging)
    }.build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .client(httpClient)
        .build()

    val retrofitService: NewsServiceInterface by lazy { retrofit.create(NewsServiceInterface::class.java) }
}