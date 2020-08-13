package com.sunilsharma.forecastmvvm.data.db.entity


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.sunilsharma.forecastmvvm.utils.DataConverter


const val CURRENT_WEATHER_ID = 0
@Entity(tableName = "current_weather")
data class CurrentWeatherEntry(
    @SerializedName("cloudcover")
    val cloudcover: Double,

    @SerializedName("feelslike")
    val feelslike: Double,

    @SerializedName("humidity")
    val humidity: Double,

    @SerializedName("is_day")
    val isDay: String,

    @SerializedName("observation_time")
    val observationTime: String,

    @SerializedName("precip")
    val precip: Double,

    @SerializedName("pressure")
    val pressure: Double,

    @SerializedName("temperature")
    val temperature: Double,

    @SerializedName("uv_index")
    val uvIndex: Double,

    @SerializedName("visibility")
    val visibility: Double,

    @SerializedName("weather_code")
    val weatherCode: Double,

    @SerializedName("weather_descriptions")
    val weatherDescriptions: List<String>,

    @SerializedName("weather_icons")
    val weatherIcons: List<String>,

    @SerializedName("wind_degree")
    val windDegree: Double,

    @SerializedName("wind_dir")

    val windDir: String,

    @SerializedName("wind_speed")
    val windSpeed: Double
){
    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_WEATHER_ID
}