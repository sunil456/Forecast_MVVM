package com.sunilsharma.forecastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.sunilsharma.forecastmvvm.data.db.entity.CurrentWeatherEntry
import com.sunilsharma.forecastmvvm.data.db.entity.WeatherLocation

interface ForecastRepository {
    suspend fun getCurrentWeather() : LiveData<out CurrentWeatherEntry>
    suspend fun getWeatherLocation() : LiveData<out WeatherLocation>
}