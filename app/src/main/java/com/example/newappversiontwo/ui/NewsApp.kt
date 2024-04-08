import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newappversiontwo.ui.screens.TopNewsItem


@Composable
fun NewsApp() {
//    val navController = rememberNavController()
//    val scrollState: ScrollState = rememberScrollState()
//    MainScreen(navController =navController , scrollState )
    Navigation()

}
//@Composable
//fun MainScreen(navController: NavHostController, scrollState: ScrollState){
//    Scaffold() {
//        Navigation(
//           // navController = navController,scrollState =scrollState, paddingValues = it
//        )
//    }
//}

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "top") {
        composable("top") { TopNews(navController) }
        composable("detail") { DetailScreen(navController) }
        composable("item-news") { TopNewsItem(navController) }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun TopNewsPreview(){
//    TopNews()
//}