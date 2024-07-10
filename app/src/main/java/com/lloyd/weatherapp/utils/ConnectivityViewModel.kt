package com.lloyd.weatherapp.utils

import androidx.lifecycle.ViewModel
import com.lloyd.weatherapp.utils.networkConnection.ConnectivityManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConnectivityViewModel@Inject constructor(private val connectivityManager: ConnectivityManager) : ViewModel() {
    fun appConnectivityStatus(): Boolean { return connectivityManager.isNetworkAvailable.value }
}