package com.lloyd.weatherapp.widgets.navigation

sealed class WeatherAppNavScreens(val name: String) {

    object Splash: WeatherAppNavScreens("splash_screen")
    object HomeScreen: WeatherAppNavScreens("home_screen")
}