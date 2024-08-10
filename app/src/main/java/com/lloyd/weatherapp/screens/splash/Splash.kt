package com.lloyd.weatherapp.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lloyd.weatherapp.R
import com.lloyd.weatherapp.screens.home.HomeViewModel
import com.lloyd.weatherapp.utils.ConnectivityViewModel
import com.lloyd.weatherapp.utils.Constants.API_KEY
import com.lloyd.weatherapp.utils.Constants.WEATHER_KEY
import com.lloyd.weatherapp.utils.network.DataState
import com.lloyd.weatherapp.widgets.navigation.WeatherAppNavScreens
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest

@Composable
fun Splash(navController: NavController) {
    // Used for side effects on submit button
    val viewModelJob = Job()
    val scope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // User view model
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val connectivityViewModel = hiltViewModel<ConnectivityViewModel>()

    val progressBar = remember { mutableStateOf(false) }
    scope.launch {
        withContext(Dispatchers.IO) {
            homeViewModel.getTodayWeather(lat = "-25.894560", lon = "28.131820", key = API_KEY)
            withContext(Dispatchers.Main) {
                homeViewModel.weatherResponse.collectLatest {
                    when(it){
                        is DataState.Success->{
                            progressBar.value = false
                            navController.currentBackStackEntry?.arguments?.putParcelable(WEATHER_KEY, it.data)
                            navController.navigate(WeatherAppNavScreens.HomeScreen.name) { launchSingleTop = true }
                        }
                        is DataState.Loading-> progressBar.value = true
                        is DataState.Error->{
                            progressBar.value = false
                        }
                    }
                }
            }
        }
    }

    Scaffold { padding ->
        val scale = remember { androidx.compose.animation.core.Animatable(0f) }
        LaunchedEffect(key1 = true) {
            scale.animateTo(targetValue = 1.0f, animationSpec = tween(durationMillis = 1000, easing = { OvershootInterpolator(2f).getInterpolation(it) }))
        }
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            if(progressBar.value) Image(painter = painterResource(id = R.drawable.weather_logo), contentDescription = "Logo", modifier = Modifier.scale(scale.value))
        }
    }
}