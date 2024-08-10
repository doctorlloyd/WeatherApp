package com.lloyd.weatherapp.screens.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lloyd.weatherapp.models.forecast.Weather
import com.lloyd.weatherapp.screens.DeviceViewModel
import com.lloyd.weatherapp.utils.Constants.WEATHER_KEY

@Suppress("DEPRECATION")
@Composable
fun HomeScreen(navController: NavController) {
    val deviceViewModel = hiltViewModel<DeviceViewModel>()
    val weather = navController.previousBackStackEntry?.arguments?.getParcelable<Weather>(WEATHER_KEY)

}