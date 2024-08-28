package com.lloyd.weatherapp.screens.search

import com.lloyd.weatherapp.models.local.LocalWeather
import com.lloyd.weatherapp.models.remote.forecast.Weather

suspend fun addLocalWeather(city: String, weather : Weather, searchScreenViewModel : SearchScreenViewModel){
    searchScreenViewModel.insertWeather(
        LocalWeather(lat = weather.coord?.lat!!,
            lon = weather.coord.lon!!,
            city = city,
            temp = weather.main?.temp!!,
            temp_max = weather.main.temp_max!!,
            temp_min = weather.main.temp_min!!,
            dt_txt = weather.dt_txt!!,
            description = weather.weather!![0].description!!,
            icon = weather.weather[0].icon!!)
    )
}