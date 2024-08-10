package com.lloyd.weatherapp.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lloyd.weatherapp.models.forecast.Weather
import com.lloyd.weatherapp.models.weekforecasts.Forecasts
import com.lloyd.weatherapp.repository.WeatherAppRepository
import com.lloyd.weatherapp.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(private val repository: WeatherAppRepository): ViewModel() {
    private val weatherDataState: MutableSharedFlow<DataState<Weather>> = MutableSharedFlow(replay = 1)
    var weatherResponse : Flow<DataState<Weather>> = weatherDataState

    private val forecastsDataState: MutableSharedFlow<DataState<Forecasts>> = MutableSharedFlow(replay = 1)
    var forecastsResponse : Flow<DataState<Forecasts>> = forecastsDataState

    suspend fun getTodayWeather(lat: String, lon: String, key: String){
        viewModelScope.launch {
            repository.getTodayWeather(lat = lat, lon = lon, key = key).collectLatest {
                weatherDataState.tryEmit(it)
            }
        }
    }
    suspend fun getWeeklyWeather(lat: String, lon: String, key: String){
        viewModelScope.launch {
            repository.getWeeklyWeather(lat = lat, lon = lon, key = key).collectLatest {
                forecastsDataState.tryEmit(it)
            }
        }
    }
}