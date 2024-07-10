package com.lloyd.weatherapp.widgets.navigation

sealed class WeatherAppNavScreens(val name: String) {
    object HomeScreen: WeatherAppNavScreens("home_screen")
}