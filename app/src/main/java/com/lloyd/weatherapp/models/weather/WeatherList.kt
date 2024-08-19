package com.lloyd.weatherapp.models.weather

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherList(
    @Json(name = "description")val description: String? = null,
    @Json(name = "icon")val icon: String? = null,
    @Json(name = "id")val id: Int? = 0,
    @Json(name = "main")val main: String? = null
) : Parcelable