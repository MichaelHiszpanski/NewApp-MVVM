
import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newappversiontwo.ui.models.MockData
import com.example.newappversiontwo.ui.components.BottomMenu
import com.example.newappversiontwo.ui.models.TopNewsArticle
import com.example.newappversiontwo.ui.network.NewsManager
import com.example.newappversiontwo.ui.screens.BottomMenuScreen
import com.example.newappversiontwo.ui.screens.CategoriesScreen
import com.example.newappversiontwo.ui.screens.SourcesScreen


@Composable
fun NewsApp() {
    val navController = rememberNavController()
    val scrollState: ScrollState = rememberScrollState()
    MainScreen(navController =navController , scrollState )


}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController, scrollState: ScrollState){
    Scaffold(bottomBar = {BottomMenu(navController)}) {
        Navigation(navController=navController,scrollState=scrollState, paddingValues = it)

    }
}

@Composable
fun Navigation(navController: NavHostController, scrollState: ScrollState,newsManager:NewsManager= NewsManager(),paddingValues:PaddingValues){
    val articles=newsManager.newsResponse.value.articles
   articles?.let{
       NavHost(navController = navController, startDestination = BottomMenuScreen.TopNews.route, modifier = Modifier.padding(paddingValues =paddingValues )) {
           bottomNavigation(navController=navController,articles,newsManager)
           composable("detail/{index}",
               arguments = listOf(navArgument("index"){type= NavType.IntType})
           ) { navBackStackEntry ->
               val index = navBackStackEntry.arguments?.getInt("index")
               index?.let {
                   val article = articles[index]
                   DetailScreen(article, scrollState, navController)
               }
           }


       }
   }
}
fun NavGraphBuilder.bottomNavigation(navController: NavController,articles:List<TopNewsArticle>,newsManager: NewsManager) {
    composable(BottomMenuScreen.TopNews.route) {
        TopNews(navController = navController, articles =articles )
    }
    composable(BottomMenuScreen.Categories.route) {
        newsManager.getArticlesByCategory("business")
        newsManager.onSelectedCategoryChanged("business")
        CategoriesScreen(newsManager=newsManager, onFetchCategory = {
            newsManager.onSelectedCategoryChanged(it)
            newsManager.getArticlesByCategory(it)
        }, navController = navController)
    }
    composable(BottomMenuScreen.Sources.route) {
        SourcesScreen()
    }
}
