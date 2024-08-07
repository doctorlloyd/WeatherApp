package com.lloyd.weatherapp.models.forecast

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sys(
    @Json(name = "country") val country: String,
    @Json(name = "id") val id: Int,
    @Json(name = "sunrise") val sunrise: Int,
    @Json(name = "sunset") val sunset: Int,
    @Json(name = "type") val type: Int
) : Parcelable