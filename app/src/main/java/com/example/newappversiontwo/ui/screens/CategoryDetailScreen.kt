package com.example.newappversiontwo.ui.screens

import DetailTopAppBar
import InfoWithIcon
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newappversiontwo.models.MockData
import com.example.newappversiontwo.models.MockData.getTimeAgo
import com.example.newappversiontwo.R
import com.example.newappversiontwo.models.TopNewsArticle
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun CategoryDetailScreen(article: TopNewsArticle, scrollState: ScrollState, navController: NavController) {

    Scaffold(topBar = {
        DetailTopAppBar(onBackPressed = { navController.popBackStack()});
    }) {contentPadding ->

        println("Aritcle 3====>")
        println(article)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(contentPadding)
                .verticalScroll(scrollState), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Detail Screen", fontWeight = FontWeight.SemiBold, color = Color.Red)

            CoilImage(
                imageModel = article.urlToImage,
                contentScale = ContentScale.Crop,
                error = ImageBitmap.imageResource(R.drawable.breaking_news),
                placeHolder = ImageBitmap.imageResource(R.drawable.breaking_news))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoWithIcon(Icons.Default.Edit, info = article.author?:"Not Available")
                InfoWithIcon(icon = Icons.Default.DateRange, info = MockData.stringToDate(article.publishedAT?:"2023-11-10T14:25:20Z").getTimeAgo())
            }

            Text(text = article.title?:"Not Available", fontWeight = FontWeight.Bold)
            Text(text = article.description?:"Not Available", modifier = Modifier.padding(top = 16.dp))

        }
    }



}



@Preview(showBackground = true)
@Composable
fun DetailCategoryScreenPreview(){
    val mockNavController = rememberNavController()

    CategoryDetailScreen(
        TopNewsArticle( author = "Namita Singh",
            title = "Cleo Smith news — live: Kidnap suspect 'in hospital again' as 'hard police grind' credited for breakthrough - The Independent",
            description = "The suspected kidnapper of four-year-old Cleo Smith has been treated in hospital for a second time amid reports he was “attacked” while in custody.",
            publishedAT = "2021-11-04T04:42:40Z"), rememberScrollState(),mockNavController)
}