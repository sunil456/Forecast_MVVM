package com.sunilsharma.forecastmvvm

import android.app.Application
import android.content.Context
import com.google.android.gms.location.LocationServices
import com.jakewharton.threetenabp.AndroidThreeTen
import com.sunilsharma.forecastmvvm.data.db.ForecastDatabase
import com.sunilsharma.forecastmvvm.data.network.*
import com.sunilsharma.forecastmvvm.data.provider.LocationProvider
import com.sunilsharma.forecastmvvm.data.provider.LocationProviderImpl
import com.sunilsharma.forecastmvvm.data.provider.UnitProvider
import com.sunilsharma.forecastmvvm.data.provider.UnitProviderImpl
import com.sunilsharma.forecastmvvm.data.repository.ForecastRepository
import com.sunilsharma.forecastmvvm.data.repository.ForecastRepositoryImpl
import com.sunilsharma.forecastmvvm.ui.weather.current.CurrentWeatherViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ForecastApplication() : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))

        bind() from singleton { ForecastDatabase( instance() ) }
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
        bind() from singleton { instance<ForecastDatabase>().weatherLocationDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApixuWeatherApiService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind() from provider { LocationServices.getFusedLocationProviderClient(instance<Context>()) }
        bind<LocationProvider>() with singleton { LocationProviderImpl(instance(), instance()) }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(),instance(), instance(), instance()) }
        bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance(), instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init( this )
    }

}