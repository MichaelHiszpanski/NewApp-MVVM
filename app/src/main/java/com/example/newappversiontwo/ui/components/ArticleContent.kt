package com.example.newappversiontwo.ui.components


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.newappversiontwo.models.TopNewsArticle
@Composable
fun ArticleContent(articles:List<TopNewsArticle>, modifier: Modifier = Modifier, onNewsClick: () -> Unit = {}, navController: NavController){
    LazyColumn{
     items(articles){
         article ->
         CategoryItem(article = article, modifier = modifier, onNewsClick = {
        onNewsClick()
        navController.navigate("Detail/${articles.indexOf(article)}")
        })
     }

    }

}
