package com.lloyd.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lloyd.weatherapp.models.local.LocalWeather
import com.lloyd.weatherapp.utils.databaseconverter.DateConverter
import com.lloyd.weatherapp.utils.databaseconverter.UUIDConverter


@Database(entities = [LocalWeather::class], version = 2, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDatabase(): WeatherDao
}