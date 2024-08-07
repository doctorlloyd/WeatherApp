package com.lloyd.weatherapp.models.forecast

import android.os.Parcelable
import com.lloyd.weatherapp.models.weather.WeatherList
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Forecast(
    @Json(name = "base") val base: String,
    @Json(name = "clouds") val clouds: Clouds,
    @Json(name = "cod") val cod: Int,
    @Json(name = "coord") val coord: Coord,
    @Json(name = "dt") val dt: Int,
    @Json(name = "id") val id: Int,
    @Json(name = "main") val main: Main,
    @Json(name = "name") val name: String,
    @Json(name = "rain") val rain: Rain,
    @Json(name = "sys") val sys: Sys,
    @Json(name = "timezone") val timezone: Int,
    @Json(name = "visibility") val visibility: Int,
    @Json(name = "weather") val weather: WeatherList,
    @Json(name = "amount") val wind: Wind
): Parcelable