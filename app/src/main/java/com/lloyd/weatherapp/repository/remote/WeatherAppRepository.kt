package com.lloyd.weatherapp.repository.remote

import com.lloyd.weatherapp.data.remote.WeatherApiService
import com.lloyd.weatherapp.models.remote.forecast.Weather
import com.lloyd.weatherapp.models.remote.weekforecasts.Forecast
import com.lloyd.weatherapp.utils.network.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherAppRepository@Inject constructor(private val weatherApiService: WeatherApiService) {
    suspend fun getTodayWeather(lat: String, lon: String, key: String): Flow<DataState<Weather>> = flow {
        emit(DataState.Loading)
        try {
            val forecast = weatherApiService.getTodayWeather(lat = lat, lon = lon, key = key)
            emit(DataState.Success(data = forecast))
        } catch (e: Exception) {
            emit(DataState.Error(exception = e))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getTodayWeatherByCity(city: String, key: String): Flow<DataState<Weather>> = flow {
        emit(DataState.Loading)
        try {
            val forecast = weatherApiService.getTodayWeatherByCity(city = city, key = key)
            emit(DataState.Success(data = forecast))
        } catch (e: Exception) {
            emit(DataState.Error(exception = e))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getWeeklyWeather(lat: String, lon: String, key: String): Flow<DataState<Forecast>> = flow {
        emit(DataState.Loading)
        try {
            val forecasts = weatherApiService.getWeeklyWeather(lat = lat, lon = lon, key = key)
            emit(DataState.Success(data = forecasts))
        } catch (e: Exception) {
            emit(DataState.Error(exception = e))
        }
    }.flowOn(Dispatchers.IO)
}