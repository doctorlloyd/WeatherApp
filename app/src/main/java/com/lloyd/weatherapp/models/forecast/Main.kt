package com.lloyd.weatherapp.models.forecast

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Main(
    @Json(name = "feels_like") val feels_like: Double,
    @Json(name = "grnd_level") val grnd_level: Int,
    @Json(name = "humidity") val humidity: Int,
    @Json(name = "pressure") val pressure: Int,
    @Json(name = "sea_level") val sea_level: Int,
    @Json(name = "temp") val temp: Double,
    @Json(name = "temp_max") val temp_max: Double,
    @Json(name = "temp_min") val temp_min: Double
) : Parcelable