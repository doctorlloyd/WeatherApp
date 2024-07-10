package com.lloyd.weatherapp.di

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.lloyd.weatherapp.data.WeatherApiService
import com.lloyd.weatherapp.models.location.DefaultLocationTracker
import com.lloyd.weatherapp.repository.WeatherAppRepository
import com.lloyd.weatherapp.utils.location.LocationTracker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideWeatherRepository(weatherApiService: WeatherApiService): WeatherAppRepository {
        return WeatherAppRepository(weatherApiService)
    }

    @Provides
    @Singleton
    fun providesFusedLocationProviderClient(application: Application): FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(application)

    @Provides
    @Singleton
    fun providesLocationTracker(fusedLocationProviderClient: FusedLocationProviderClient, application: Application): LocationTracker = DefaultLocationTracker(fusedLocationProviderClient = fusedLocationProviderClient, application = application)

}