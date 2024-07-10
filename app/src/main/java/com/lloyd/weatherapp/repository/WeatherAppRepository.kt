package com.lloyd.weatherapp.repository

import com.lloyd.weatherapp.data.WeatherApiService
import javax.inject.Inject

class WeatherAppRepository@Inject constructor(private val weatherApiService: WeatherApiService) {

}