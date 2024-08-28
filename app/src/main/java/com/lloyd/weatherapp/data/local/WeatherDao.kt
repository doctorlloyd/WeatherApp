package com.lloyd.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lloyd.weatherapp.models.local.Weather

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun insertWeather(weather: Weather)

    @Query("SELECT * from weather_tbl")
    fun getListOfRecentlySearchedWeather():List<Weather>?

    @Query("DELETE FROM weather_tbl")
    fun deleteRecentlySearchedWeather()
}