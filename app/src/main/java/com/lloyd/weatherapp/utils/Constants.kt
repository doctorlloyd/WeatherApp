package com.lloyd.weatherapp.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.AddToHomeScreen
import androidx.compose.material.icons.automirrored.outlined.AddToHomeScreen
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material.icons.outlined.PersonSearch
import com.lloyd.weatherapp.models.navigation.BottomNavigationItem

object Constants {
    const val WEATHER_KEY: String = "weather_object_key"
    const val FAHRENHEIT: Double = 273.15

    val navScreens = listOf(BottomNavigationItem(title = "Home Screen", selectedIcon = Icons.AutoMirrored.Filled.AddToHomeScreen, unselectedIcon = Icons.AutoMirrored.Outlined.AddToHomeScreen, hasNews = false),
        BottomNavigationItem(title = "Search Screen", selectedIcon = Icons.Filled.PersonSearch, unselectedIcon = Icons.Outlined.PersonSearch, hasNews = false))
}