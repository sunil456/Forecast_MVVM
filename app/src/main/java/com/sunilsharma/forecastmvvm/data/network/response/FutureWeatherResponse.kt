package com.sunilsharma.forecastmvvm.data.network.response

import com.google.gson.annotations.SerializedName
import com.sunilsharma.forecastmvvm.data.db.entity.WeatherLocation

data class FutureWeatherResponse(
    @SerializedName("forecast")
    val futureWeatherEntries: ForecastDaysContainer,
    @SerializedName("location")
    val weatherLocation: WeatherLocation
)