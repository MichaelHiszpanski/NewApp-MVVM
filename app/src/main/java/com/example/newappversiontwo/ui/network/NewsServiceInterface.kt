package com.example.newappversiontwo.ui.network

import com.example.newappversiontwo.ui.models.TopNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServiceInterface {
    @GET("top-headlines")
    fun getTopArticles(@Query("country") country:String,
                       @Query("apiKey") apiKey:String): Call<TopNewsResponse>
}