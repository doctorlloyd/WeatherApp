package com.lloyd.weatherapp.models.weekforecasts

import android.os.Parcelable
import com.lloyd.weatherapp.models.forecast.Coord
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(
    @Json(name = "coord")val coord: Coord? = Coord(),
    @Json(name = "country")val country: String? = null,
    @Json(name = "id")val id: Int? = 0,
    @Json(name = "name")val name: String? = null,
    @Json(name = "population")val population: Int? = 0,
    @Json(name = "sunrise")val sunrise: Int? = 0,
    @Json(name = "sunset")val sunset: Int? = 0,
    @Json(name = "timezone")val timezone: Int? = 0
) : Parcelable