package com.example.newappversiontwo.ui.models

import javax.xml.transform.Source

data class TopNewsArticle(
    val source: Source?=null,
    val author:String?=null,
    val title:String?=null,
    val description:String?=null,
    val url:String?=null,
    val urlToImage:String?=null,
    val publishedAT:String?=null,
    val content:String?=null
)
