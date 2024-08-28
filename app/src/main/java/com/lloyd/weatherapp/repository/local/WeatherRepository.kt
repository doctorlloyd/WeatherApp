package com.lloyd.weatherapp.repository.local

import com.lloyd.weatherapp.data.local.WeatherDao
import com.lloyd.weatherapp.models.local.LocalWeather
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

class WeatherRepository@Inject constructor(private val weatherDao: WeatherDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    suspend fun insertWeather(localWeather: LocalWeather): Long = coroutineScope.async{
        return@async weatherDao.insertWeather(localWeather = localWeather)
    }.await()

    suspend fun getListOfRecentlySearchedWeather(): List<LocalWeather>? = coroutineScope.async{
        return@async weatherDao.getListOfRecentlySearchedWeather()
    }.await()

    suspend fun deleteRecentlySearchedWeather(): Int = coroutineScope.async{
        return@async weatherDao.deleteRecentlySearchedWeather()
    }.await()
}