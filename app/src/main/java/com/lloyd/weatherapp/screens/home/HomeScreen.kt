package com.lloyd.weatherapp.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lloyd.weatherapp.R
import com.lloyd.weatherapp.models.forecast.Weather
import com.lloyd.weatherapp.models.weekforecasts.Forecast
import com.lloyd.weatherapp.screens.DeviceViewModel
import com.lloyd.weatherapp.utils.*
import com.lloyd.weatherapp.widgets.ConnectivityViewModel
import com.lloyd.weatherapp.utils.network.DataState
import com.lloyd.weatherapp.utils.theme.Cloudy
import com.lloyd.weatherapp.utils.theme.Rainy
import com.lloyd.weatherapp.utils.theme.Sunny
import com.lloyd.weatherapp.widgets.loader.Loader
import com.lloyd.weatherapp.widgets.search.CustomSearchViewRight
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

    val weeklyWeather = remember { mutableStateOf(Forecast())}

    if(!weather.value.name.isNullOrEmpty() && weeklyWeather.value.list!!.isNotEmpty()) WeatherWidget(weather = weather, weeklyWeather = weeklyWeather)
    else scope.launch {
        if(connectivityViewModel.appConnectivityStatus() && deviceViewModel.currentLocation?.latitude != null){
            withContext(Dispatchers.IO) {
                homeViewModel.getTodayWeather(lat = deviceViewModel.currentLocation?.latitude.toString(), lon = deviceViewModel.currentLocation?.longitude.toString(), key = Constants.API_KEY)
                withContext(Dispatchers.Main) {
                    homeViewModel.weatherResponse.collectLatest {
                        when(it){
                            is DataState.Success->{
                                weather.value = it.data
                                homeViewModel.forecastsResponse.collectLatest { week ->
                                    when(week) {
                                        is DataState.Success -> { progressBar.value = false
                                            weeklyWeather.value = week.data }
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
fun WeatherWidget(weather: MutableState<Weather>, weeklyWeather: MutableState<Forecast>){
    val weatherList: MutableList<Weather> = mutableListOf()
    var searchText by remember { mutableStateOf("") }

    if(weeklyWeather.value.list?.isNotEmpty() == true){
        for (w in weeklyWeather.value.list!!){
            if(isBetween11AM2PM(w.dt_txt!!)){
                weatherList.add(w)
            }
        }
    }

    if(weatherList.size >= 5) Box(modifier = Modifier.fillMaxSize().padding(0.dp)){
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = with (Modifier){ fillMaxSize().weight(0.8f).paint(painterResource(id =
            if(backgroundColorChanger(weather.value.weather?.get(0)?.icon!!).contains("clear", ignoreCase = true)) R.drawable.forest_sunny
            else if(backgroundColorChanger(weather.value.weather?.get(0)?.icon!!).contains("rain", ignoreCase = true)) R.drawable.forest_rainy
            else R.drawable.forest_cloudy
            ), contentScale = ContentScale.FillBounds) }){
                CustomSearchViewRight(placeholder = "Search for your favourite city.", search = searchText, modifier = Modifier.align(Alignment.TopCenter).fillMaxWidth().background(color = Color.Transparent).padding(start = 16.dp, end = 16.dp, top = 8.dp), onValueChange = { text -> searchText = text }, weather = {
                    // navigate to another screen

                })
                Column(modifier = Modifier.align(Alignment.Center),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "${"%.0f".format(convertTemperature(weather.value.main?.temp!!))} \u00B0C", color = Color.White, style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold))
                    Text(text = weather.value.weather?.get(0)?.description!!, color = Color.White, style = TextStyle(fontSize = 22.sp))
                }
            }
            Row(modifier = Modifier.fillMaxWidth().weight(0.2f).background(color =
            if(backgroundColorChanger(weather.value.weather?.get(0)?.icon!!).contains("clear", ignoreCase = true)) Sunny
            else if(backgroundColorChanger(weather.value.weather?.get(0)?.icon!!).contains("rain", ignoreCase = true)) Rainy
            else Cloudy), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically){
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
            Column(modifier = Modifier.fillMaxWidth().weight(1f).background(color =
            if(backgroundColorChanger(weather.value.weather?.get(0)?.icon!!).contains("clear", ignoreCase = true)) Sunny
            else if(backgroundColorChanger(weather.value.weather?.get(0)?.icon!!).contains("rain", ignoreCase = true)) Rainy
            else Cloudy).padding(top = 14.dp)){
                LazyColumn(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(getWeekDays().size) { i ->
                        Row(modifier = Modifier.fillMaxWidth().padding(end = 14.dp, start = 14.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly){
                            Text(textAlign = TextAlign.Center, text = getWeekDays()[i], color = Color.White)
                            Image(painter = painterResource(id = getWeatherIcon(weatherList[i].weather!![0].icon!!)), contentDescription = null)
                            Text(textAlign = TextAlign.Center, text = "${"%.0f".format(convertTemperature(weatherList[i].main?.temp!!))} \u00B0C", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}