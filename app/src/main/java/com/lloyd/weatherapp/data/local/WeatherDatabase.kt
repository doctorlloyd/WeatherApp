package com.lloyd.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lloyd.weatherapp.models.local.Weather
import com.lloyd.weatherapp.utils.databaseconverter.DateConverter
import com.lloyd.weatherapp.utils.databaseconverter.UUIDConverter


@Database(entities = [Weather::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDatabase(): WeatherDao
}