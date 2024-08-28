package com.lloyd.weatherapp.models.remote.forecast

import android.os.Parcelable
import com.lloyd.weatherapp.models.remote.weather.WeatherList
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    @Json(name = "base") val base: String? = null,
    @Json(name = "clouds") val clouds: Clouds? = Clouds(),
    @Json(name = "cod") val cod: Int? = 0,
    @Json(name = "coord") val coord: Coord? = Coord(),
    @Json(name = "dt") val dt: Int? = 0,
    @Json(name = "id") val id: Int? = 0,
    @Json(name = "main") val main: Main? = Main(),
    @Json(name = "name") val name: String? = null,
    @Json(name = "sys") val sys: Sys? = Sys(),
    @Json(name = "timezone") val timezone: Int? = 0,
    @Json(name = "visibility") val visibility: Int? = 0,
    @Json(name = "weather") val weather: List<WeatherList>? = ArrayList(),
    @Json(name = "amount") val wind: Wind? = Wind(),
    @Json(name = "dt_txt") val dt_txt: String? = null,
): Parcelable