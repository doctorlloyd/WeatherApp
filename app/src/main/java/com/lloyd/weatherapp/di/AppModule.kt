package com.lloyd.weatherapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.lloyd.weatherapp.data.local.WeatherDao
import com.lloyd.weatherapp.data.local.WeatherDatabase
import com.lloyd.weatherapp.data.remote.WeatherApiService
import com.lloyd.weatherapp.models.remote.location.DefaultLocationTracker
import com.lloyd.weatherapp.repository.remote.WeatherAppRepository
import com.lloyd.weatherapp.utils.location.LocationTracker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun providesWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao = weatherDatabase.weatherDatabase()

    @Singleton
    @Provides
    fun provideWeatherAppDatabase(@ApplicationContext context: Context): WeatherDatabase = Room.databaseBuilder(context, WeatherDatabase::class.java, "weather_db")
        .fallbackToDestructiveMigration()
        .build()

}