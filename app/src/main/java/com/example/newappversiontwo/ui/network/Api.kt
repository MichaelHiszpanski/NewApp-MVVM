package com.example.newappversiontwo.ui.network

import com.squareup.moshi.Moshi

import okhttp3.Interceptor
import okhttp3.OkHttpClient

import retrofit2.Retrofit


object Api {
    const val API_KEY="d3562306af44495d99f915fa7817657d"
    private val BASE_URL = "https://newsapi.org/v2/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val logging=HttpLoggingInterceptor()

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
        //.addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .client(httpClient)
        .build()

    val retrofitService: NewsServiceInterface by lazy { retrofit.create(NewsServiceInterface::class.java) }
}