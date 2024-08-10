package com.lloyd.weatherapp.models.weekforecasts

import android.os.Parcelable
import com.lloyd.weatherapp.models.forecast.Weather
import kotlinx.parcelize.Parcelize

@Parcelize
class Forecasts : ArrayList<Weather>(), Parcelable