package com.example.newappversiontwo.ui.mvvm

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.newappversiontwo.MainApp
import com.example.newappversiontwo.models.ArticleCategory
import com.example.newappversiontwo.models.TopNewsResponse
import com.example.newappversiontwo.models.getArticleCategoryFromEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(application:Application):AndroidViewModel(application) {
    private val repository=getApplication<MainApp>().repository

    private  val _newsReponse=MutableStateFlow(TopNewsResponse())
    val newsResponse:StateFlow<TopNewsResponse>
        get() =_newsReponse

    private  val _isLoading=MutableStateFlow(true)
    val isLoading:StateFlow<Boolean> = _isLoading

    fun getTopArticles(){
        _isLoading.value=true
        viewModelScope.launch(Dispatchers.IO){
            _newsReponse.value=repository.getArticles()
        }
        _isLoading.value=false
    }
    private val _getArticlesAll= MutableStateFlow(TopNewsResponse())
    val getArticlesAll:StateFlow<TopNewsResponse>
        get() = _getArticlesAll

    private val _getArticleByCategory= MutableStateFlow(TopNewsResponse())
    val getArticleByCategory:StateFlow<TopNewsResponse>
       get() = _getArticleByCategory

    private val _selectedCategory: MutableStateFlow<ArticleCategory?> = MutableStateFlow(null)
    val selectedCategory:StateFlow<ArticleCategory?>
        get() = _selectedCategory
    fun getArticlesByCategory(category:String){
        _isLoading.value=true
        viewModelScope.launch(Dispatchers.IO){
            _getArticleByCategory.value=repository.getArticlesByCategory(category)
        }
        _isLoading.value=false
    }

    fun onSelectedCategoryChanged(category:String){
        val newCategory= getArticleCategoryFromEnum(category=category)
        _selectedCategory.value=newCategory
    }
}