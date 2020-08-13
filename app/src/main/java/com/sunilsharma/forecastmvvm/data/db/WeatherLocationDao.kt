package com.sunilsharma.forecastmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sunilsharma.forecastmvvm.data.db.entity.WEATHER_LOCATION_ID
import com.sunilsharma.forecastmvvm.data.db.entity.WeatherLocation

@Dao
interface WeatherLocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherLocation: WeatherLocation)

    @Query("SELECT * FROM weather_location WHERE id= $WEATHER_LOCATION_ID")
    fun getLocation(): LiveData<WeatherLocation>
}