package com.lloyd.weatherapp.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.AddToHomeScreen
import androidx.compose.material.icons.automirrored.outlined.AddToHomeScreen
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material.icons.outlined.PersonSearch
import com.lloyd.weatherapp.models.navigation.BottomNavigationItem
import java.text.SimpleDateFormat
import java.util.*

object Constants {
    const val FAHRENHEIT: Double = 273.15
    val dtFormat  = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)

    val navScreens = listOf(BottomNavigationItem(title = "Home Screen", selectedIcon = Icons.AutoMirrored.Filled.AddToHomeScreen, unselectedIcon = Icons.AutoMirrored.Outlined.AddToHomeScreen, hasNews = false),
        BottomNavigationItem(title = "Search Screen", selectedIcon = Icons.Filled.PersonSearch, unselectedIcon = Icons.Outlined.PersonSearch, hasNews = false))
}