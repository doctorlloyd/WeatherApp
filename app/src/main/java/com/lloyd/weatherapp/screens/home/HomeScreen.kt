package com.lloyd.weatherapp.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lloyd.weatherapp.R
import com.lloyd.weatherapp.models.forecast.Weather
import com.lloyd.weatherapp.models.weekforecasts.Forecast
import com.lloyd.weatherapp.screens.DeviceViewModel
import com.lloyd.weatherapp.widgets.ConnectivityViewModel
import com.lloyd.weatherapp.utils.Constants
import com.lloyd.weatherapp.utils.convertTemperature
import com.lloyd.weatherapp.utils.network.DataState
import com.lloyd.weatherapp.utils.theme.Sunny
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("SuspiciousIndentation")
@Suppress("DEPRECATION")
@Composable
fun HomeScreen(navController: NavController) {
    // Used for side effects on submit button
    val viewModelJob = Job()
    val scope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // User view model
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val connectivityViewModel = hiltViewModel<ConnectivityViewModel>()
    val deviceViewModel = hiltViewModel<DeviceViewModel>()
    deviceViewModel.getCurrentLocation()

    val progressBar = remember { mutableStateOf(false) }
    val weather = remember { mutableStateOf(Weather())}
    val weekelyWeather = remember { mutableStateOf(Forecast())}

    if(!weather.value.name.isNullOrEmpty()) WeatherWidget(weather = weather, forecasts = weekelyWeather)
    else scope.launch {
        if(connectivityViewModel.appConnectivityStatus() && deviceViewModel.currentLocation?.latitude != null){
            withContext(Dispatchers.IO) {
                homeViewModel.getTodayWeather(lat = deviceViewModel.currentLocation?.latitude.toString(), lon = deviceViewModel.currentLocation?.longitude.toString(), key = Constants.API_KEY)
                withContext(Dispatchers.Main) {
                    homeViewModel.weatherResponse.collectLatest {
                        when(it){
                            is DataState.Success->{
                                weather.value = it.data
                                homeViewModel.forecastsResponse.collectLatest { weekely ->
                                    when(weekely) {
                                        is DataState.Success -> { progressBar.value = false
                                            weekelyWeather.value = weekely.data }
                                        is DataState.Loading -> {}
                                        is DataState.Error -> progressBar.value = false
                                    }
                                }
                            }
                            is DataState.Loading -> progressBar.value = true
                            is DataState.Error -> progressBar.value = false
                        }
                    }
                }
            }
        }
    }
    if(progressBar.value) Loader()
}

@Composable
fun Loader() { CircularProgressIndicator(modifier = Modifier.fillMaxSize(), color = Color.White) }

@Composable
fun WeatherWidget(weather: MutableState<Weather>, forecasts: MutableState<Forecast>){
    Box(modifier = Modifier.fillMaxSize().padding(0.dp)){
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = with (Modifier){ fillMaxSize().weight(0.9f).paint(painterResource(id = R.drawable.forest_sunny), contentScale = ContentScale.FillBounds) }){
                Column(modifier = Modifier.align(Alignment.Center),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "${"%.0f".format(convertTemperature(weather.value.main?.temp!!))} \u00B0C", color = Color.White, style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold))
                    Text(text = weather.value.weather?.get(0)?.description!!, color = Color.White, style = TextStyle(fontSize = 22.sp))
                }
            }
            Row(modifier = Modifier.fillMaxWidth().weight(0.2f).background(color = Sunny), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically){
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "${"%.0f".format(convertTemperature(weather.value.main!!.temp_min!!))} \u00B0C", color = Color.White)
                    Text(text = "min", color = Color.White)
                }
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "${"%.0f".format(convertTemperature(weather.value.main?.temp!!))} \u00B0C", color = Color.White)
                    Text(text = "Current", color = Color.White)
                }
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "${"%.0f".format(convertTemperature(weather.value.main?.temp_max!!))} \u00B0C", color = Color.White)
                    Text(text = "max", color = Color.White)
                }
            }
            Divider(color = Color.White, thickness = 2.dp)
            Column(modifier = Modifier.fillMaxWidth().weight(0.9f).background(color = Sunny)){
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                    println("------------------\n${forecasts.value.cod}")
                }
            }
        }
    }
}