package com.feriantes4dawin.feriavirtualmovil.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.feriantes4dawin.feriavirtual.misc.exceptions.NoConnectionException
import okhttp3.Interceptor
import okhttp3.Response


class ConnectivityInterceptorImpl
constructor (private var c : Context) : ConnectivityInterceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isOnline())
            throw NoConnectionException()

        return chain.proceed(chain.request())
    }

    private fun isOnline():Boolean {

        /* Esto ayudará a saber si la aplicacion está conectada a internet */
        val connectivityManager = c.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected?: false

    }

}