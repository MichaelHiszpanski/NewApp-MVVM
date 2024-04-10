package com.example.newappversiontwo

import android.app.Application
import com.example.newappversiontwo.data.Repository
import com.example.newappversiontwo.network.Api
import com.example.newappversiontwo.network.NewsManager

class MainApp: Application() {
    private val manager by lazy{
        NewsManager(Api.retrofitService)
    }

    val repository by lazy{
        Repository(manager=manager)
    }

}