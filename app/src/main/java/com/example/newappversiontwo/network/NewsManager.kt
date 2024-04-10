package com.example.newappversiontwo.network

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import com.example.newappversiontwo.models.ArticleCategory
import com.example.newappversiontwo.models.TopNewsResponse
import com.example.newappversiontwo.models.getArticleCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsManager(private val service: NewsServiceInterface) {
    private val _newsResponse= mutableStateOf(TopNewsResponse())

    val newsResponse:State<TopNewsResponse>
        @Composable get()=remember {
            _newsResponse
        }
    val selectedCategory:MutableState<ArticleCategory?> = mutableStateOf(null)
    val sourceName= mutableStateOf("abc-news")
    private val _getArticleBySource=mutableStateOf(TopNewsResponse())
    val getArticleBySource: MutableState<TopNewsResponse>
        @Composable get() = remember {
            _getArticleBySource
        }


    private val _searchNewsResponse=mutableStateOf(TopNewsResponse())
    val searchNeWsResponse: MutableState<TopNewsResponse>
        @Composable get() = remember {
            _searchNewsResponse
        }
    val query= mutableStateOf("")

    suspend  fun getArticles(country:String): TopNewsResponse = withContext(Dispatchers.IO){
        service.getTopArticles(country=country)

    }
    suspend fun getArticlesByCategory(category:String): TopNewsResponse = withContext(Dispatchers.IO){

        service.getArticlesByCategory(category=category)
    }

    fun onSelectedCategoryChanged(category:String){
        val newCategory= getArticleCategory(category=category)
        selectedCategory.value=newCategory
    }




    fun getArticlesBySource(){
        val service = Api.retrofitService.getArticlesBySource(sourceName.value)
        service.enqueue(object : Callback<TopNewsResponse> {
            override fun onResponse(call: Call<TopNewsResponse>, response: Response<TopNewsResponse>) {
                if (response.isSuccessful){
                    _getArticleBySource.value = response.body()!!
                    Log.d("source","${_getArticleBySource.value}")
                }else{
                    Log.d("error","${response.code()}")
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                Log.d("error","${t.message}")
            }

        })

    }

    // Search
    fun getSearchedArticles(query:String){
        val service = Api.retrofitService.getArticles(query)
        service.enqueue(object : Callback<TopNewsResponse> {
            override fun onResponse(call: Call<TopNewsResponse>, response: Response<TopNewsResponse>) {
                if (response.isSuccessful){
                    _searchNewsResponse.value = response.body()!!
                    Log.d("*search","${_searchNewsResponse.value}")
                }else{
                    Log.d("*search error","${response.code()}")
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                Log.d("*search error","${t.message}")
            }

        })

    }
}