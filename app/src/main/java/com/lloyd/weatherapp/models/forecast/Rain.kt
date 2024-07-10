package com.lloyd.weatherapp.models.forecast

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rain(
    val `1h`: Double
) : Parcelable