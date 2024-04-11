package com.example.newappversiontwo.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.newappversiontwo.R
import com.example.newappversiontwo.models.MockData
import com.example.newappversiontwo.models.MockData.getTimeAgo
import com.example.newappversiontwo.models.TopNewsArticle

@Composable
fun TopNewsItem(article: TopNewsArticle, onNewsClick:()->Unit={}){
    Box(modifier = Modifier
        .height(200.dp)
        .padding(8.dp)
        .clickable {
            print("Hello 1 =====>")
            println(article)
            onNewsClick()
        }) {
        com.skydoves.landscapist.coil.CoilImage(imageModel = article.urlToImage, contentScale = ContentScale.Crop, error = ImageBitmap.imageResource(
            R.drawable.breaking_news), placeHolder = ImageBitmap.imageResource(
            R.drawable.breaking_news))
        Column(modifier = Modifier
            .wrapContentHeight()
            .padding(top = 16.dp, start = 16.dp),verticalArrangement = Arrangement.SpaceBetween) {
            Text(text =
           // MockData.stringToDate(
                article.publishedAT?:"2021-11-10T14:25:20Z"
          //  ).getTimeAgo()
                ,color = Color.White,fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(100.dp))
            Text(text = article.title?:"Not Available",color = Color.White,fontWeight = FontWeight.SemiBold)
        }
    }
}
