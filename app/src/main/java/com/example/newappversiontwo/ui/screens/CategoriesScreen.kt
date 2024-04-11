package com.example.newappversiontwo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.newappversiontwo.ui.components.ArticleContent
import com.example.newappversiontwo.ui.components.CategoryTab
import com.example.newappversiontwo.models.getAllArticleCategory
import com.example.newappversiontwo.network.NewsManager
import com.example.newappversiontwo.ui.mvvm.MainViewModel

@Composable
fun CategoriesScreen(onFetchCategory:(String)->Unit={}, viewModel:MainViewModel, navController:NavController){
    val tabsItems= getAllArticleCategory()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val articlesByCategory by viewModel.getArticleByCategory.collectAsState()
    Column {
        LazyRow {
            items(tabsItems.size){
                val category=tabsItems[it]
                CategoryTab(
                    category = category.categoryName,
                    onFetchCategory=onFetchCategory,
                    isSelected = selectedCategory == category)
            }
        }
    ArticleContent(articles =articlesByCategory.articles?:listOf(), navController =navController )
    }
}