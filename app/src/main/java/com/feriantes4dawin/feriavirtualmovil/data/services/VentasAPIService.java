package com.feriantes4dawin.feriavirtualmovil.data.services;

import com.feriantes4dawin.feriavirtualmovil.data.network.responses.VentasDisponibleResponse;
import com.feriantes4dawin.feriavirtualmovil.ui.util.FeriaVirtualConstants;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

//https://www.youtube.com/watch?v=RSYTn-O3r34

public interface VentasAPIService {

    @GET("ventas/usuario/{idusuario}")
    VentasDisponibleResponse getVentasDisponibles(
        //Funcion que indica que valor json se supone que se recuperara
        @Path(value="idusuario") int idusuario

    );

}