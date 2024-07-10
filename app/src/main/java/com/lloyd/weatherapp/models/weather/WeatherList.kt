package com.lloyd.weatherapp.models.weather

import android.os.Parcelable
import com.lloyd.weatherapp.models.forecast.Weather
import kotlinx.parcelize.Parcelize

@Parcelize
class WeatherList : ArrayList<Weather>(), Parcelable