package com.lloyd.weatherapp.models.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "weather_tbl")
data class Weather(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recordId")
    val recordId: Int = 0,
    @ColumnInfo(name = "lat")val lat: Double,
    @ColumnInfo(name = "lon")val lon: Double,
    @ColumnInfo(name = "dt") val dt: Int? = 0,
    @ColumnInfo(name = "id") val id: Int? = 0,
    @ColumnInfo(name = "temp") val temp: Double,
    @ColumnInfo(name = "temp_max") val temp_max: Double,
    @ColumnInfo(name = "temp_min") val temp_min: Double,
    @ColumnInfo(name = "dt_txt") val dt_txt: String,
    @ColumnInfo(name = "description")val description: String? = null,
    @ColumnInfo(name = "icon")val icon: String? = null
): Parcelable