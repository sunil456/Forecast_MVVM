package com.sunilsharma.forecastmvvm.data.provider

import com.sunilsharma.forecastmvvm.data.db.entity.WeatherLocation

interface LocationProvider {
    suspend fun hasLastLocationChanged(lastWeatherLocation: WeatherLocation) : Boolean
    suspend fun getPreferredLocationString(): String
}