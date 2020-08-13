package com.sunilsharma.forecastmvvm.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sunilsharma.forecastmvvm.data.network.response.CurrentWeatherResponse
import com.sunilsharma.forecastmvvm.data.network.response.FutureWeatherResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//
const val API_KEY = "ce3cc59665013b6fe85f4babbf68ac8f"


//http://api.weatherstack.com/current?access_key=ce3cc59665013b6fe85f4babbf68ac8f&query=mumbai
interface ApixuWeatherApiService {

    @GET("current")
    fun getCurrentWeather(
        @Query("query") location: String,
        @Query("lang") languageCode: String = "en"
    ) : Deferred<CurrentWeatherResponse>

//    http://api.weatherstack.com/forecast?access_key=05e6e560c529d13e865b981ddabd2bb4&query=New%20York&forecast_days=1&hourly=1
    @GET("forecast.json")
    fun getFutureWeather(
        @Query("query") location: String,
        @Query("forecast_days") days: Int,
        @Query("lang") languageCode: String = "en"
    ): Deferred<FutureWeatherResponse>

    companion object{

        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): ApixuWeatherApiService{

            val requestInterceptor = Interceptor{chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key", API_KEY)
                    .build()


                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)

            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.weatherstack.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApixuWeatherApiService::class.java)
        }

    }
}