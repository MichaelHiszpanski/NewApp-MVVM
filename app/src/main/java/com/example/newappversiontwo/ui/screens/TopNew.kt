import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newappversiontwo.MockData
import com.example.newappversiontwo.ui.screens.TopNewsItem


@Composable
fun TopNews(navController: NavController){
    Column ( modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Top News", fontWeight = FontWeight.SemiBold, color = Color.Red, fontSize =22.sp )
        Button(onClick = {
            navController.navigate("detail")
        }) {
            Text(text = "Go toDetail Screen")
        }
        LazyColumn{

            items(MockData.topNewsList){
                    newsData->   TopNewsItem(newsData = newsData, onNewsClick ={
                        navController.navigate("detail/${newsData.id}")
            } )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopNewsPreview(){

    TopNews( rememberNavController())
}