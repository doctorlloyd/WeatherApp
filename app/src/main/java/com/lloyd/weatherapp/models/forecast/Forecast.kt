package com.lloyd.weatherapp.models.forecast

import android.os.Parcelable
import com.lloyd.weatherapp.models.weather.WeatherList
import kotlinx.parcelize.Parcelize

@Parcelize
data class Forecast(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val rain: Rain,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: WeatherList,
    val wind: Wind
): Parcelable