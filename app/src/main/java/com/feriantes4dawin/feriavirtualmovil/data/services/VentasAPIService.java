package com.feriantes4dawin.feriavirtualmovil.data.services;

import com.feriantes4dawin.feriavirtualmovil.data.models.Ventas;

import retrofit2.http.GET;
import retrofit2.http.Path;

//https://www.youtube.com/watch?v=RSYTn-O3r34

public interface VentasAPIService {

    @GET("ventas/usuario/{idusuario}")
    Ventas getVentasDisponibles(
        //Funcion que indica que valor json se supone que se recuperara
        @Path(value="idusuario") int idusuario

    );

}