package com.feriantes4dawin.feriavirtualmovil.dependencies

import com.feriantes4dawin.feriavirtual.data.services.UsuarioAPIService
import com.feriantes4dawin.feriavirtual.ui.util.FeriaVirtualConstants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FeriaVirtualAPIProvider {

    fun provideUsuarioAPI(): UsuarioAPIService {

        return commonAPIBuilder().build().
        create(UsuarioAPIService::class.java)

    }

    private fun commonAPIBuilder() : Retrofit.Builder{

        val httpClient = OkHttpClient.Builder().build()

        //Crea un builder para transformar los datos a enviar a la api
        return Retrofit.Builder().

            //Se establece el objeto que gestionara las peticiones http para retrofit
        client(httpClient).

            //Esto indica la url base que la api tiene.
        baseUrl(FeriaVirtualConstants.URL_BASE_API_WEB_FERIAVIRTUAL).

            //Esto activa el mecanismo de coroutines de Kotlin, usando un adapter que
            //gestiona dichos procesos
        addCallAdapterFactory(CoroutineCallAdapterFactory()).

            //Y e aquí lo más importante: El objeto que se encargará de convertir
            //cada objeto de dato en JSON para nuestra api
        addConverterFactory(GsonConverterFactory.create())
    }

}