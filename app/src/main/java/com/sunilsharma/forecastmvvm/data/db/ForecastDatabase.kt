package com.sunilsharma.forecastmvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sunilsharma.forecastmvvm.data.db.entity.CurrentWeatherEntry
import com.sunilsharma.forecastmvvm.data.db.entity.FutureWeatherEntry
import com.sunilsharma.forecastmvvm.data.db.entity.WeatherLocation
import com.sunilsharma.forecastmvvm.utils.DataConverter

@Database(
    entities = [CurrentWeatherEntry::class, WeatherLocation::class, FutureWeatherEntry::class],
    version = 1
)

@TypeConverters(DataConverter::class,LocalDateConverter::class)
abstract class ForecastDatabase : RoomDatabase() {

    abstract fun currentWeatherDao() : CurrentWeatherDao
    abstract fun weatherLocationDao() : WeatherLocationDao
    abstract fun futureWeatherDao() : FutureWeatherDao

    companion object {
        @Volatile private var instance: ForecastDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ForecastDatabase::class.java, "futureWeatherEntries.db")
                .build()
    }
}