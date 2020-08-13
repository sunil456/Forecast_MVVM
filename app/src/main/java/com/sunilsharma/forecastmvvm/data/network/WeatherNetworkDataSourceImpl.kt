package com.sunilsharma.forecastmvvm.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sunilsharma.forecastmvvm.data.network.response.CurrentWeatherResponse
import com.sunilsharma.forecastmvvm.data.network.response.FutureWeatherResponse
import com.sunilsharma.forecastmvvm.internal.NoConnectivityException

const val FORECAST_DAYS_COUNT = 7

class WeatherNetworkDataSourceImpl(
    private val apixuWeatherApiService: ApixuWeatherApiService
) : WeatherNetworkDataSource {

    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    private val _downloadedFutureWeather = MutableLiveData<FutureWeatherResponse>()

    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override val downloadedFutureWeather: LiveData<FutureWeatherResponse>
        get() = _downloadedFutureWeather

    override suspend fun fetchCurrentWeather(location: String, languageCode: String) {
        try {
            val fetchCurrentWeather = apixuWeatherApiService
                .getCurrentWeather(location, languageCode)
                .await()
            _downloadedCurrentWeather.postValue(fetchCurrentWeather)
        }
        catch (e: NoConnectivityException)
        {
            Log.d("SUNIL", "fetchCurrentWeather:  NoConnectivityException " , e)
        }
    }

    override suspend fun fetchFutureWeather(location: String, languageCode: String) {
        try {
            val fetchedFutureWeather = apixuWeatherApiService
                .getFutureWeather(location, FORECAST_DAYS_COUNT, languageCode)
                .await()
            _downloadedFutureWeather.postValue(fetchedFutureWeather)
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }
}