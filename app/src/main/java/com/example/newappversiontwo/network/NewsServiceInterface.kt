package com.example.newappversiontwo.network

import com.example.newappversiontwo.models.TopNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServiceInterface {
    @GET("top-headlines")
    suspend fun getTopArticles(@Query("country") country:String): TopNewsResponse

    @GET("top-headlines")
    suspend fun getArticlesByCategory(@Query("category")category:String
    ): TopNewsResponse

    @GET("everything")
    fun getArticlesBySource(@Query("sources")source:String
    ): Call<TopNewsResponse>

    //Search
    @GET("everything")
    fun getArticles(@Query("q")query:String
    ): Call<TopNewsResponse>
}