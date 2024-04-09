package com.example.newappversiontwo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.newappversiontwo.ui.network.NewsManager
import androidx.compose.foundation.lazy.items
@Composable
fun SourcesScreen(newsManager: NewsManager){
        val items= listOf(
            "TechCrunch" to "techcrunch",
            "TalkSport" to "talksport",
            "Business Insider" to "business-insider",
            "Reuters" to "reuters",
            "Politico" to "politico",
            "TheVerge" to "the-verge"
        )

        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "${newsManager.sourceName.value} Source") },
                actions = {
                    var menuExpanded by remember { mutableStateOf(false) }
                    IconButton(onClick = {menuExpanded=true}) {
                        Icon(Icons.Default.MoreVert, contentDescription = null)
                    }
                    MaterialTheme(shapes = MaterialTheme.shapes.copy(medium =
                    RoundedCornerShape(16.dp)
                    )) {
                        DropdownMenu(expanded = menuExpanded,
                            onDismissRequest = {menuExpanded=false}) {
                            items.forEach {
                                DropdownMenuItem(onClick = {
                                    newsManager.sourceName.value=it.second
                                    menuExpanded=false
                                }) {
                                    Text(it.first)

                                }
                            }
                        }
                    }
                })
        }) {contentPadding ->
            newsManager.getArticlesBySource()
            val articles=newsManager.getArticleBySource.value
           Column(modifier = Modifier
               //.fillMaxSize()
              // .padding(16.dp)
               .padding(contentPadding) ){
               SourceContent(articles = articles.articles?: listOf())
           }
        }
    }
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
