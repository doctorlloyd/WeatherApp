package com.lloyd.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lloyd.weatherapp.models.local.LocalWeather

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun insertWeather(localWeather: LocalWeather): Long

    @Query("SELECT * from weather_tbl ORDER BY dt_txt DESC")
    fun getListOfRecentlySearchedWeather(): List<LocalWeather>?

    @Query("DELETE FROM weather_tbl")
    fun deleteRecentlySearchedWeather(): Int
}