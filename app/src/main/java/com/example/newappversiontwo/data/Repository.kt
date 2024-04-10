package com.example.newappversiontwo.data

import com.example.newappversiontwo.network.NewsManager

class Repository(val manager:NewsManager) {
    suspend fun  getArticles()=manager.getArticles("gb")
    suspend fun getArticlesByCategory(category:String)=manager.getArticlesByCategory(category=category)
}