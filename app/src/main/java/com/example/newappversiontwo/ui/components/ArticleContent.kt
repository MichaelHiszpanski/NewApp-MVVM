package com.example.newappversiontwo.ui.components


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newappversiontwo.models.TopNewsArticle
@Composable
fun ArticleContent(articles:List<TopNewsArticle>, modifier: Modifier = Modifier, onNewsClick: () -> Unit = {}, navController: NavController){
    if (articles.isNotEmpty()) {
//        println("Aritcles1 ====>")
//        print(articles)
        LazyColumn {
            items(articles) { article ->
                println("Aritcle 2====>")
                println(articles)
                CategoryItem(article = article, modifier = modifier, onNewsClick = {
                    print("Result==>")
                    println(articles.indexOf(article))
                    navController.navigate("Detail/${articles.indexOf(article)}")
                })
            }
        }
    } else {
        Text("Loading articles or no articles available.", modifier = modifier.padding(16.dp))
    }

}
