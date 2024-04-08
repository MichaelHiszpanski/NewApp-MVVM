
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newappversiontwo.MockData
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
        composable("detail/{newsId}",
            arguments = listOf(navArgument("newsId"){type= NavType.IntType})
        ) { navBackStackEntry ->
            val id =navBackStackEntry.arguments?.getInt("newsId")
            val newsData=MockData.getNews(id)
            DetailScreen(navController,newsData) }
        composable("item-news") { TopNewsItem(newsData = MockData.topNewsList[0]) }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun TopNewsPreview(){
//    TopNews()
//}