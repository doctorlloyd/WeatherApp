package com.lloyd.weatherapp.models.forecast

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coord(
    @Json(name = "lat")val lat: Double,
    @Json(name = "lon")val lon: Double
) : Parcelable