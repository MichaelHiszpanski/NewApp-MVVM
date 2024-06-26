package com.example.newappversiontwo.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class  BottomMenuScreen(val route:String,val icon: ImageVector, val title:String){

    object TopNews: BottomMenuScreen("top", icon = Icons.Outlined.Home,"Top News")
    object Categories: BottomMenuScreen("categories", icon = Icons.Outlined.Face,"Categories")
    object Sources: BottomMenuScreen("sources", icon = Icons.Outlined.Settings,"Sources")
}