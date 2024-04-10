package com.example.newappversiontwo.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.newappversiontwo.R
import com.example.newappversiontwo.models.TopNewsArticle
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun CategoryItem(article: TopNewsArticle, modifier: Modifier = Modifier, onNewsClick: () -> Unit = {}){

    Card(modifier.padding(8.dp),
        border = BorderStroke(2.dp, color = colorResource(id = R.color.purple_500)) ) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {
                    onNewsClick()
                }


        ) {
            CoilImage(
                imageModel = article.urlToImage
                , modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop,
                placeHolder = ImageBitmap.imageResource(R.drawable.error),//1 painterResource(id = R.drawable.error),
                error = ImageBitmap.imageResource(R.drawable.error),//painterResource(id = R.drawable.error)
            )
            Column(modifier.padding(8.dp)) {
                Text(text = article.title?:"Not Available", fontWeight = FontWeight.Bold,
                    maxLines = 3, overflow = TextOverflow.Ellipsis)
                Row (
                    modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(text = article.author?:"Not Available")
                    //Text(text = MockData.stringToDate(article.publishedAT?:"2022-03-08T20:45:20").getTimeAgo())



                }
            }
        }


    }
}