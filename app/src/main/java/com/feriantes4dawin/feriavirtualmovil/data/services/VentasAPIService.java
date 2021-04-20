package com.feriantes4dawin.feriavirtualmovil.data.services;

import com.feriantes4dawin.feriavirtualmovil.data.network.responses.VentasDisponibleResponse;
import com.feriantes4dawin.feriavirtualmovil.ui.util.FeriaVirtualConstants;
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory;
import kotlinx.coroutines.Deferred;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

//https://www.youtube.com/watch?v=RSYTn-O3r34

public interface VentasAPIService {

    @GET("ventas/usuario/{idusuario}")
    public VentasDisponibleResponse getVentasDisponibles(
        //Funcion que indica que valor json se supone que se recuperara
        @Path(value="idusuario") int idusuario

    );

}