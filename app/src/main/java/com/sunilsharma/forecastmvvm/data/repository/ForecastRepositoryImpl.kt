package com.sunilsharma.forecastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.sunilsharma.forecastmvvm.data.db.CurrentWeatherDao
import com.sunilsharma.forecastmvvm.data.db.WeatherLocationDao
import com.sunilsharma.forecastmvvm.data.db.entity.CurrentWeatherEntry
import com.sunilsharma.forecastmvvm.data.db.entity.WeatherLocation
import com.sunilsharma.forecastmvvm.data.network.ApixuWeatherApiService
import com.sunilsharma.forecastmvvm.data.network.WeatherNetworkDataSource
import com.sunilsharma.forecastmvvm.data.network.response.CurrentWeatherResponse
import com.sunilsharma.forecastmvvm.data.provider.LocationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherLocationDao: WeatherLocationDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource,
    private val locationProvider: LocationProvider
) : ForecastRepository {

    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever { newCurrentWeather ->
            persistFetchCurrentWeather(newCurrentWeather)
        }
    }

    override suspend fun getCurrentWeather(): LiveData<out CurrentWeatherEntry> {
        return withContext(Dispatchers.IO) {
            initWeatherData()
            currentWeatherDao.getCurrentWeather()
        }
    }

    override suspend fun getWeatherLocation(): LiveData<out WeatherLocation> {
        return withContext(Dispatchers.IO) {
            return@withContext weatherLocationDao.getLocation()
        }
    }

    private fun persistFetchCurrentWeather(fetchedWeather: CurrentWeatherResponse)
    {
        GlobalScope.launch(Dispatchers.IO){
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
            weatherLocationDao.upsert(fetchedWeather.weatherLocation)
        }
    }

    private suspend fun initWeatherData() {

        val lastWeatherLocation = weatherLocationDao.getLocation().value

        if (lastWeatherLocation == null || locationProvider.hasLastLocationChanged(lastWeatherLocation))
        {
            fetchCurrentWeather()
            return
        }

        if (isFetchCurrentNeeded(lastWeatherLocation.zonedDateTime))
            fetchCurrentWeather()


//        if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
//            fetchCurrentWeather()
    }



    private suspend fun fetchCurrentWeather()
    {
        weatherNetworkDataSource.fetchCurrentWeather(
            locationProvider.getPreferredLocationString(),
            Locale.getDefault().language
        )
    }

    private fun isFetchCurrentNeeded(lastFatchTime: ZonedDateTime): Boolean
    {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)

        return lastFatchTime.isBefore(thirtyMinutesAgo)
    }




}