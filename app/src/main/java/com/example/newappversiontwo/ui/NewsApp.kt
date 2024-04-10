
import android.annotation.SuppressLint
import android.util.Log
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
import com.example.newappversiontwo.models.MockData
import com.example.newappversiontwo.ui.components.BottomMenu
import com.example.newappversiontwo.models.TopNewsArticle
import com.example.newappversiontwo.network.Api
import com.example.newappversiontwo.network.NewsManager
import com.example.newappversiontwo.ui.mvvm.MainViewModel
import com.example.newappversiontwo.ui.screens.BottomMenuScreen
import com.example.newappversiontwo.ui.screens.CategoriesScreen
import com.example.newappversiontwo.ui.screens.SourcesScreen


@Composable
fun NewsApp(mainViewModel:MainViewModel) {
    val navController = rememberNavController()
    val scrollState: ScrollState = rememberScrollState()
    MainScreen(navController =navController , scrollState ,mainViewModel=mainViewModel)


}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController, scrollState: ScrollState,mainViewModel:MainViewModel){
    Scaffold(bottomBar = {BottomMenu(navController)}) {
        Navigation(navController=navController,scrollState=scrollState, paddingValues = it, mainViewModel = mainViewModel)

    }
}

@Composable
fun Navigation(navController: NavHostController, scrollState: ScrollState, newsManager: NewsManager = NewsManager(
    Api.retrofitService), paddingValues:PaddingValues,mainViewModel:MainViewModel){
    val articles = mutableListOf(TopNewsArticle())
    articles.addAll(newsManager.newsResponse.value.articles ?: listOf(TopNewsArticle()))
    Log.d("NEws ===>","$articles")
   articles?.let{
       NavHost(navController = navController, startDestination = BottomMenuScreen.TopNews.route, modifier = Modifier.padding(paddingValues =paddingValues )) {
           bottomNavigation(navController=navController,articles,newsManager, mainViewModel = mainViewModel)
           composable("detail/{index}",
               arguments = listOf(navArgument("index"){type= NavType.IntType})
           ) { navBackStackEntry ->
               val index = navBackStackEntry.arguments?.getInt("index")
               index?.let {
                   if (newsManager.query.value.isNotEmpty()) {
                       articles.clear()
                       articles.addAll(newsManager.searchNeWsResponse.value.articles?: listOf())
                   }else{
                       articles.clear()
                       articles.addAll(newsManager.newsResponse.value.articles?: listOf())
                   }
                   val article = articles[index]
                   DetailScreen(article, scrollState, navController)
               }
           }


       }
   }
}
fun NavGraphBuilder.bottomNavigation(navController: NavController, articles:List<TopNewsArticle>, newsManager: NewsManager,mainViewModel:MainViewModel) {
    composable(BottomMenuScreen.TopNews.route) {
        TopNews(navController = navController, articles =articles,newsManager=newsManager, query = newsManager.query )
    }
    composable(BottomMenuScreen.Categories.route) {
        mainViewModel.getArticlesByCategory("business")
        mainViewModel.onSelectedCategoryChanged("business")
        CategoriesScreen(viewModel =mainViewModel , onFetchCategory = {
           mainViewModel.onSelectedCategoryChanged(it)
            mainViewModel.getArticlesByCategory(it)
        }, navController = navController)
    }
    composable(BottomMenuScreen.Sources.route) {
        SourcesScreen(newsManager)
    }
}
