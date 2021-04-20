package com.feriantes4dawin.feriavirtualmovil.data.services

import com.feriantes4dawin.feriavirtual.data.network.responses.VentasDisponibleResponse
import com.feriantes4dawin.feriavirtual.ui.util.FeriaVirtualConstants.Companion.URL_BASE_API_WEB_FERIAVIRTUAL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//https://www.youtube.com/watch?v=RSYTn-O3r34

interface VentasAPIService {

    @GET("ventas/usuario/{idusuario}")
    fun getVentasDisponibles(
        //Funcion que indica que valor json se supone que se recuperara
        @Path(value="idusuario") idusuario:Int

    ):Deferred<VentasDisponibleResponse>

}