package com.sunilsharma.forecastmvvm.data.network.response


import com.google.gson.annotations.SerializedName
import com.sunilsharma.forecastmvvm.data.db.entity.CurrentWeatherEntry
import com.sunilsharma.forecastmvvm.data.db.entity.WeatherLocation
import com.sunilsharma.forecastmvvm.data.db.entity.Request

data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    @SerializedName("location")
    val weatherLocation: WeatherLocation,
    @SerializedName("request")
    val request: Request
)