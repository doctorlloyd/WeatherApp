package com.lloyd.weatherapp.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lloyd.weatherapp.screens.DeviceViewModel
import com.lloyd.weatherapp.utils.ConnectivityViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val connectivityViewModel = hiltViewModel<ConnectivityViewModel>()
//    val deviceViewModel = hiltViewModel<DeviceViewModel>()
    LaunchedEffect(key1 = connectivityViewModel.appConnectivityStatus(),
        block ={

        })
}