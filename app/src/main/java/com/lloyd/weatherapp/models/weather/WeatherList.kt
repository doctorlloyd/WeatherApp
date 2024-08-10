package com.lloyd.weatherapp.models.weather

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherList(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
) : Parcelable