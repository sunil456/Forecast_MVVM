package com.sunilsharma.forecastmvvm.ui.weather.current

import androidx.lifecycle.ViewModel
import com.sunilsharma.forecastmvvm.data.provider.UnitProvider
import com.sunilsharma.forecastmvvm.data.repository.ForecastRepository
import com.sunilsharma.forecastmvvm.internal.UnitSystem
import com.sunilsharma.forecastmvvm.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : ViewModel() {

    private val unitSystem = unitProvider.getUnitSystem() // get from Setting latter

    val isMatric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather() //or forecastRepository.getCurrentWeather(isMatric)
    }

    val weatherLocation by lazyDeferred {
        forecastRepository.getWeatherLocation()
    }
}