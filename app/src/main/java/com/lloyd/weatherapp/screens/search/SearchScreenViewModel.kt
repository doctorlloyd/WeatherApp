package com.lloyd.weatherapp.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lloyd.weatherapp.models.local.LocalWeather
import com.lloyd.weatherapp.repository.local.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel@Inject constructor(private val repository: WeatherRepository): ViewModel() {

    suspend fun getListOfRecentlySearchedWeather(): List<LocalWeather>? {
        val deferred: Deferred<List<LocalWeather>?> = viewModelScope.async {
            repository.getListOfRecentlySearchedWeather()
        }
        return deferred.await()
    }

    suspend fun deleteRecentlySearchedWeather(): Int {
        val deferred: Deferred<Int> = viewModelScope.async {
            repository.deleteRecentlySearchedWeather()
        }
        return deferred.await()
    }

    suspend fun insertWeather(localWeather: LocalWeather): Long {
        val deferred: Deferred<Long> = viewModelScope.async {
            repository.insertWeather(localWeather = localWeather)
        }
        return deferred.await()
    }
}