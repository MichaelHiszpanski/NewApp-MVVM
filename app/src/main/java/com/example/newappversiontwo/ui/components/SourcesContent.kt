package com.example.newappversiontwo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.newappversiontwo.R
import com.example.newappversiontwo.ui.models.TopNewsArticle

@Composable
fun SourceContent(articles:List<TopNewsArticle>){
    val uriHandler= LocalUriHandler.current

    LazyColumn{
        items(articles){
                article->
            val annotatedString= buildAnnotatedString {
                pushStringAnnotation(
                    tag = "URL",
                    annotation = article.url?:"newsapi.org"
                )
                withStyle(style = SpanStyle(color = colorResource(id = R.color.purple_500),
                    textDecoration= TextDecoration.Underline)
                ){
                    append("Read Full Article Here")
                }
            }

            Card(backgroundColor = colorResource(id = R.color.purple_700),
                elevation =6.dp, modifier = Modifier.padding(8.dp) ) {

                Column(modifier = Modifier
                    .height(200.dp)
                    .padding(end = 8.dp, start = 8.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = article.title?:"Not Available",
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = article.description?:"Not Available",
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis
                    )
                    Card(backgroundColor = Color.White, elevation = 6.dp ) {
                        ClickableText(text = annotatedString, modifier = Modifier.padding(8.dp),onClick = {
                            annotatedString.getStringAnnotations(it,it).firstOrNull()?.let {
                                    result ->
                                if (result.tag=="URL"){
                                    uriHandler.openUri(result.item)
                                }
                            }
                        })
                    }
                }

            }

        }
    }
}