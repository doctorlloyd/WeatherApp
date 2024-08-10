package com.lloyd.weatherapp.widgets.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lloyd.weatherapp.screens.home.HomeScreen
import com.lloyd.weatherapp.screens.splash.Splash

@Composable
fun WeatherAppNavWidget() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherAppNavScreens.Splash.name) {
        composable(WeatherAppNavScreens.Splash.name) {
            Splash(navController = navController)
        }
        composable(WeatherAppNavScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
    }
}