package com.lloyd.weatherapp.models.forecast

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wind(
    @Json(name = "deg") val deg: Int? = 0,
    @Json(name = "gust") val gust: Double? = 0.00,
    @Json(name = "speed") val speed: Double? = 0.00
) : Parcelable