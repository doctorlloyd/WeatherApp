package com.lloyd.weatherapp.di

import com.lloyd.weatherapp.data.WeatherApiService
import com.lloyd.weatherapp.repository.WeatherAppRepository
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
}