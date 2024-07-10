package com.lloyd.weatherapp.data

import com.lloyd.weatherapp.models.forecast.Forecast
import com.lloyd.weatherapp.models.weekforecasts.Forecasts
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface WeatherApiService {
    @GET("lat={lat}&lon={lon}&appid={key}")
    suspend fun getTodayWeather(@Path("lat") lat: String,
                                @Path("lon") lon: String,
                                @Path("key") key: String): Forecast

    @GET("lat={lat}&lon={lon}&appid={key}")
    suspend fun getWeeklyWeather(@Path("lat") lat: String,
                                 @Path("lon") lon: String,
                                 @Path("key") key: String): Forecasts
}