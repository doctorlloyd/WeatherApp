package com.hogwarts.weatherapp.models

import com.google.gson.annotations.SerializedName

data class Clouds (

	@SerializedName("all") val all : Int
)