package com.lloyd.weatherapp.screens.search

import com.lloyd.weatherapp.models.local.LocalWeather
import com.lloyd.weatherapp.models.remote.forecast.Weather
import com.lloyd.weatherapp.utils.Constants.dtFormat
import java.util.*

suspend fun addLocalWeather(weather : Weather, searchScreenViewModel : SearchScreenViewModel){
    searchScreenViewModel.insertWeather(
        LocalWeather(lat = weather.coord?.lat!!,
            lon = weather.coord.lon!!,
            city = weather.name!!,
            temp = weather.main?.temp!!,
            temp_max = weather.main.temp_max!!,
            temp_min = weather.main.temp_min!!,
            dt_txt = dtFormat.format(Date()),
            description = weather.weather!![0].description!!,
            icon = weather.weather[0].icon!!)
    )
}