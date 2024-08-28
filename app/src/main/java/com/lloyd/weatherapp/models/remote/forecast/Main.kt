package com.lloyd.weatherapp.models.remote.forecast

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Main(
    @Json(name = "feels_like") val feels_like: Double? = 0.00,
    @Json(name = "grnd_level") val grnd_level: Int? = 0,
    @Json(name = "humidity") val humidity: Int? = 0,
    @Json(name = "pressure") val pressure: Int? = 0,
    @Json(name = "sea_level") val sea_level: Int? = 0,
    @Json(name = "temp") val temp: Double? = 0.00,
    @Json(name = "temp_max") val temp_max: Double? = 0.00,
    @Json(name = "temp_min") val temp_min: Double? = 0.00
) : Parcelable