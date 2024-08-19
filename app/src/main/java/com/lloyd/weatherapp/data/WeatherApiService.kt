package com.lloyd.weatherapp.data

import com.lloyd.weatherapp.models.forecast.Weather
import com.lloyd.weatherapp.models.weekforecasts.Forecast
import com.lloyd.weatherapp.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("/data/2.5/weather")
    suspend fun getTodayWeather(@Query("lat") lat: String,
                                @Query("lon") lon: String,
                                @Query("appid") key: String = API_KEY): Weather

    @GET("/data/2.5/forecast")
    suspend fun getWeeklyWeather(@Query("lat") lat: String,
                                 @Query("lon") lon: String,
                                 @Query("appid") key: String = API_KEY): Forecast
}