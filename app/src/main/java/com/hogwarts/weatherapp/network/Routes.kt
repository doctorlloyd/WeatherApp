package com.hogwarts.weatherapp.network

import com.hogwarts.weatherapp.models.LocationWeather
import com.hogwarts.weatherapp.models.WiFi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Routes {

    @POST("wifi/")
    fun postWifi(
        @HeaderMap headers: Map<String, String>,
        @Body wifi: WiFi
    ): Call<ResponseBody>

    @POST("wifi-list/")
    fun postWifiList(
        @HeaderMap headers: Map<String, String>,
        @Body wifi: List<WiFi>
    ): Call<ResponseBody>

    @GET("https://api.openweathermap.org/data/2.5/weather")
    fun getWeather(@HeaderMap headers: Map<String, String>, @Path("zip") zip: String, key: String): Call<LocationWeather>
}