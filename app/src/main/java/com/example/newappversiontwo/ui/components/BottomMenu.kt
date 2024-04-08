package com.example.newappversiontwo.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.example.newappversiontwo.R
import com.example.newappversiontwo.ui.screens.BottomMenuScreen

@Composable
fun BottomMenu(navController: NavController){
    val menuItems= listOf(BottomMenuScreen.TopNews, BottomMenuScreen.Categories, BottomMenuScreen.Sources)
   BottomNavigation (contentColor = colorResource(id = R.color.white) ){

   }
}