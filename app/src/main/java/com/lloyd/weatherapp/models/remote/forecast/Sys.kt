package com.lloyd.weatherapp.models.remote.forecast

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sys(
    @Json(name = "country") val country: String? = null,
    @Json(name = "id") val id: Int? = 0,
    @Json(name = "sunrise") val sunrise: Int? = 0,
    @Json(name = "sunset") val sunset: Int? = 0,
    @Json(name = "type") val type: Int? = 0
) : Parcelable