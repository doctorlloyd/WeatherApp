package com.lloyd.weatherapp.utils

import com.lloyd.weatherapp.R
import com.lloyd.weatherapp.utils.Constants.FAHRENHEIT
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

fun convertTemperature(kelvin: Double): Double {
    return kelvin - FAHRENHEIT
}

fun getWeatherIcon(code: String): Int {
    return when (code) {
        "01d" -> R.mipmap.sun_light      // Clear sky during the day
        "01n" -> R.mipmap.half_moon_light     // Clear sky during the night
        "02d" -> R.mipmap.partial_cloudy_light      // Few clouds during the day
        "02n" -> R.mipmap.cloudy_night_light    // Few clouds during the night
        "03d" -> R.mipmap.cloud_light
        "03n" -> R.mipmap.cloudy_night_stars_light // Scattered clouds (day/night)
        "04d" -> R.mipmap.mostly_cloud_light
        "04n" -> R.mipmap.double_cloudy_night_light // Broken clouds (day/night)
        "09d", "09n" -> R.mipmap.drop_light // Shower rain (day/night)
        "10d" -> R.mipmap.rainyday_light            // Rain during the day
        "10n" -> R.mipmap.heavy_rain_light          // Rain during the night
        "11d", "11n" -> R.mipmap.thunderstorm_light // Thunderstorm (day/night)
        "13d", "13n" -> R.mipmap.heavy_snowfall_light         // Snow (day/night)
        "50d", "50n" -> R.mipmap.mostly_cloudy_light         // Mist (day/night)
        else -> R.mipmap.eclipse_light      // Default icon for unknown codes
    }
}

fun backgroundColorChanger(code: String):String{
    return when (code) {
        "01d" -> "clear"
        "01n" -> "clear"
        "02d" -> "cloud"
        "02n" -> "cloud"
        "03d", "03n" -> "cloud"
        "04d", "04n" -> "cloud"
        "09d", "09n" -> "rain"
        "10d" -> "rain"
        "10n" -> "rain"
        "11d", "11n" -> "cloud"
        "13d", "13n" -> "cloud"
        "50d", "50n" -> "cloud"
        else -> "clear"
    }
}

fun getWeekDays():Array<String> {
    val today = LocalDate.now()
    val weekDays = Array(5) { "" }
    for (i in 0..4) {
        val futureDate = today.plusDays(i.toLong() + 1)
        weekDays[i] = futureDate.dayOfWeek.name
    }
    return weekDays
}

fun isBetween11AM2PM(dateString: String): Boolean {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    val date = dateFormat.parse(dateString)

    val cal = Calendar.getInstance()
    cal.time = date!!

    val cal11AM = Calendar.getInstance().apply {
        time = date
        set(Calendar.HOUR_OF_DAY, 11)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
    }

    val cal2PM = Calendar.getInstance().apply {
        time = date
        set(Calendar.HOUR_OF_DAY, 14)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
    }

    return cal.time.after(cal11AM.time) && cal.time.before(cal2PM.time)
}