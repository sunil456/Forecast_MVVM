package com.sunilsharma.forecastmvvm.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.sunilsharma.forecastmvvm.internal.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptorImpl (
    context: Context
) : ConnectivityInterceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkConnected())
            throw NoConnectivityException()
        return chain.proceed(chain.request())
    }

    private fun isNetworkConnected() : Boolean
    {
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnected
    }
}