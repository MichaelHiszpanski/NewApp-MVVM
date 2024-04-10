package com.example.newappversiontwo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.newappversiontwo.ui.components.ArticleContent
import com.example.newappversiontwo.ui.components.CategoryTab
import com.example.newappversiontwo.models.getAllArticleCategory
import com.example.newappversiontwo.network.NewsManager
import com.example.newappversiontwo.ui.mvvm.MainViewModel

@Composable
fun CategoriesScreen(onFetchCategory:(String)->Unit={}, viewModel:MainViewModel, navController:NavController){
    val tabsItems= getAllArticleCategory()


    Column {
        LazyRow {
            items(tabsItems.size){
                val category=tabsItems[it]
                CategoryTab(
                    category = category.categoryName,
                    onFetchCategory=onFetchCategory,
                    isSelected = viewModel.selectedCategory.value==category)
            }
        }
    ArticleContent(articles = viewModel.getArticleByCategory.value.articles?:listOf(), navController =navController )
    }
}