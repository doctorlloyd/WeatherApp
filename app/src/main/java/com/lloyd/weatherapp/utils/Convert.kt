package com.lloyd.weatherapp.utils

import com.lloyd.weatherapp.utils.Constants.FAHRENHEIT

fun convertTemperature(kelvin: Double): Double {
    return kelvin - FAHRENHEIT
}