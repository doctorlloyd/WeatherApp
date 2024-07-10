package com.lloyd.weatherapp.models.forecast

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rain(
    @Json(name = "1h") val `1h`: Double
) : Parcelable