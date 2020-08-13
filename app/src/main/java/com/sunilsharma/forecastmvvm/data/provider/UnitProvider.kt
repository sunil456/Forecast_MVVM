package com.sunilsharma.forecastmvvm.data.provider

import com.sunilsharma.forecastmvvm.internal.UnitSystem

interface UnitProvider {
    fun getUnitSystem() : UnitSystem
}