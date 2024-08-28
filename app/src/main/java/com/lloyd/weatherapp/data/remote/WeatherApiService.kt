package com.lloyd.weatherapp.data.remote

import com.lloyd.weatherapp.models.remote.forecast.Weather
import com.lloyd.weatherapp.models.remote.weekforecasts.Forecast
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("/data/2.5/weather")
    suspend fun getTodayWeather(@Query("lat") lat: String,
                                @Query("lon") lon: String,
                                @Query("appid") key: String): Weather

    @GET("/data/2.5/weather")
    suspend fun getTodayWeatherByCity(@Query("q") city: String,
                                    @Query("appid") key: String): Weather

    @GET("/data/2.5/forecast")
    suspend fun getWeeklyWeather(@Query("lat") lat: String,
                                 @Query("lon") lon: String,
                                 @Query("appid") key: String): Forecast
}