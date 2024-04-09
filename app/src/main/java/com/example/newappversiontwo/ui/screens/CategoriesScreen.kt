package com.example.newappversiontwo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.newappversiontwo.ui.components.CategoryTab
import com.example.newappversiontwo.ui.models.getAllArticleCategory
import com.example.newappversiontwo.ui.network.NewsManager

@Composable
fun CategoriesScreen(onFetchCategory:(String)->Unit={},newsManager:NewsManager){
    val tabsItems= getAllArticleCategory()
    Text(text = "Categories Screen")
    Column {
        LazyRow {
            items(tabsItems.size){
                val category=tabsItems[it]
                CategoryTab(
                    category = category.categoryName,
                    onFetchCategory=onFetchCategory,
                    isSelected = newsManager.selectedCategory.value==category)
            }
        }
    }
}