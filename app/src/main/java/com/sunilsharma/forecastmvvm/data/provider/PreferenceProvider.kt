package com.sunilsharma.forecastmvvm.data.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

abstract class PreferenceProvider(context: Context) {

    private val appContaxt = context.applicationContext

    val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContaxt)
}