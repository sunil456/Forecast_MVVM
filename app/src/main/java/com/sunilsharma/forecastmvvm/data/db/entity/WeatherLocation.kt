package com.sunilsharma.forecastmvvm.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

const val WEATHER_LOCATION_ID = 0
@Entity(tableName = "Weather_location")
data class WeatherLocation(
    @SerializedName("country")
    val country: String, // United Kingdom
    @SerializedName("lat")
    val lat: Double, // 51.517
    @SerializedName("localtime")
    val localtime: String, // 2020-08-06 10:17
    @SerializedName("localtime_epoch")
    val localtimeEpoch: Long, // 1596709020
    @SerializedName("lon")
    val lon: Double, // -0.106
    @SerializedName("name")
    val name: String, // London
    @SerializedName("region")
    val region: String, // City of London, Greater London
    @SerializedName("timezone_id")
    val timezoneId: String, // Europe/London
    @SerializedName("utc_offset")
    val utcOffset: String // 1.0
){
    @PrimaryKey(autoGenerate = false)
    var id : Int = WEATHER_LOCATION_ID

    val zonedDateTime: ZonedDateTime
        get() {
            val instant = Instant.ofEpochSecond(localtimeEpoch)
            val zonedId = ZoneId.of(timezoneId)

            return ZonedDateTime.ofInstant(instant, zonedId)
        }
}