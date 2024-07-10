package com.lloyd.weatherapp.models.forecast

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Clouds(
    @Json(name = "all")val all: Int
): Parcelable