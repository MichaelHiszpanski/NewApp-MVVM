import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newappversiontwo.ui.components.SearchBar
import com.example.newappversiontwo.ui.components.TopNewsItem
import com.example.newappversiontwo.models.TopNewsArticle
import com.example.newappversiontwo.network.NewsManager


@Composable
fun TopNews(navController: NavController, articles:List<TopNewsArticle>, newsManager: NewsManager, query: MutableState<String>){
    Column ( modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Top News", fontWeight = FontWeight.SemiBold, color = Color.Red, fontSize =22.sp )
        SearchBar(query =query , newsManager =newsManager )
        val searchedText = query.value
        val resultList = mutableListOf<TopNewsArticle>()
        if (searchedText != "") {
            resultList.addAll(newsManager.searchNeWsResponse.value.articles?: articles)
        }else{
            resultList.addAll(articles)
        }
        LazyColumn{

            items(resultList.size){
                    index->
                TopNewsItem(article = resultList[index], onNewsClick ={navController.navigate("Detail/$index")} )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopNewsPreview(){
    val newsManager= NewsManager()
    TopNews( rememberNavController(), listOf(
        TopNewsArticle( author = "Namita Singh",
        title = "Cleo Smith news — live: Kidnap suspect 'in hospital again' as 'hard police grind' credited for breakthrough - The Independent",
        description = "The suspected kidnapper of four-year-old Cleo Smith has been treated in hospital for a second time amid reports he was “attacked” while in custody.",
        publishedAT = "2021-11-04T04:42:40Z")
    ), newsManager =newsManager , query = newsManager.query
    )
}