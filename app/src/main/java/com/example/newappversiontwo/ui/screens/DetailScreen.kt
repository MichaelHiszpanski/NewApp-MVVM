import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newappversiontwo.ui.models.MockData
import com.example.newappversiontwo.ui.models.MockData.getTimeAgo
import com.example.newappversiontwo.ui.models.NewsData
import com.example.newappversiontwo.R
import com.example.newappversiontwo.ui.models.TopNewsArticle
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun DetailScreen(article: TopNewsArticle, scrollState: ScrollState, navController: NavController) {
    Scaffold(topBar = {
        DetailTopAppBar(onBackPressed = { navController.popBackStack()});
    }) {contentPadding ->


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
                // Crop, Fit, Inside, FillHeight, FillWidth, None
                contentScale = ContentScale.Crop,
                error = ImageBitmap.imageResource(R.drawable.breaking_news),
                // shows a placeholder ImageBitmap when loading.
                placeHolder = ImageBitmap.imageResource(R.drawable.breaking_news))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoWithIcon(Icons.Default.Edit, info = article.author?:"Not Available")
                InfoWithIcon(icon = Icons.Default.DateRange, info = MockData.stringToDate(article.publishedAT?:"2021-11-10T14:25:20Z").getTimeAgo())
            }

            Text(text = article.title?:"Not Available", fontWeight = FontWeight.Bold)
            Text(text = article.description?:"Not Available", modifier = Modifier.padding(top = 16.dp))



        }
    }



}

@Composable
fun InfoWithIcon(icon: ImageVector, info: String) {
    Row {
        Icon(
            icon,
            contentDescription = "Author",
            modifier = Modifier.padding(end = 8.dp),
            colorResource(
                id = R.color.purple_500
            )
        )
        Text(text = info)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopAppBar(onBackPressed:()-> Unit = {}){
    SmallTopAppBar(
        title = { Text(text = "Detail Screen", fontWeight = FontWeight.SemiBold) },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    )
}
@Preview(showBackground = true)
@Composable
fun DetailScreenPreview(){
    val mockNavController = rememberNavController()

    DetailScreen(TopNewsArticle( author = "Namita Singh",
        title = "Cleo Smith news — live: Kidnap suspect 'in hospital again' as 'hard police grind' credited for breakthrough - The Independent",
        description = "The suspected kidnapper of four-year-old Cleo Smith has been treated in hospital for a second time amid reports he was “attacked” while in custody.",
        publishedAT = "2021-11-04T04:42:40Z"), rememberScrollState(),mockNavController)
}