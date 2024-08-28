package com.lloyd.weatherapp.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lloyd.weatherapp.utils.Constants.navScreens
import com.lloyd.weatherapp.widgets.navigation.WeatherAppNavScreens
import com.lloyd.weatherapp.widgets.search.CustomSearchViewRight


@Composable
fun SearchScreen(navController: NavController) {
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(1) }
    var searchText by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            NavigationBar(modifier = Modifier.height(65.dp), containerColor = MaterialTheme.colors.surface, contentColor = MaterialTheme.colors.primaryVariant, tonalElevation = 2.dp) {
                navScreens.forEachIndexed { index, item ->
                    NavigationBarItem(selected = selectedItemIndex == index,
                        onClick = { selectedItemIndex = index
                            when (index) {
                                0 -> navController.navigate(WeatherAppNavScreens.HomeScreen.name){ launchSingleTop = true }
                                1 -> {}
                            }
                        },
                        label = { Text(text = item.title, style = TextStyle(fontSize = 12.sp, textAlign = TextAlign.Center, color = MaterialTheme.colors.secondary, fontWeight = if (index == selectedItemIndex) FontWeight.ExtraBold else FontWeight.Bold)) },
                        alwaysShowLabel = true, icon = { Icon(tint = if (index == selectedItemIndex) Color.White else MaterialTheme.colors.secondary, imageVector = if (index == selectedItemIndex) item.selectedIcon else item.unselectedIcon, contentDescription = item.title) },
                        colors = NavigationBarItemDefaults.colors(indicatorColor = MaterialTheme.colors.secondary)
                    )
                }
            }
        }){
        Box(modifier = Modifier.fillMaxSize().padding(0.dp)){
            CustomSearchViewRight(navController = navController, placeholder = "Search for your favourite city.", search = searchText, modifier = Modifier.align(
                Alignment.TopCenter).fillMaxWidth().background(color = Color.Transparent).padding(start = 16.dp, end = 16.dp, top = 8.dp), onValueChange = { text -> searchText = text }, weather = {
                // navigate to another screen

            })
        }
    }
}