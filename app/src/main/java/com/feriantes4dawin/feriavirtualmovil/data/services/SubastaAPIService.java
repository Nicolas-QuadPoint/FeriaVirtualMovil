package com.feriantes4dawin.feriavirtualmovil.data.services;

import com.feriantes4dawin.feriavirtualmovil.data.models.ResultadoID;
import com.feriantes4dawin.feriavirtualmovil.data.models.Subasta;
import com.feriantes4dawin.feriavirtualmovil.data.models.Subastas;
import com.feriantes4dawin.feriavirtualmovil.data.network.responses.UsuarioResponse;

import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SubastaAPIService {

    @POST("subasta/productor/{idsubasta}/pujar")
    ResultadoID pujarSubastaProductor(

            @Field(value="idsubasta")
                    Integer id_subasta

    );

    @GET("subasta/productor/{idusuario}")
    Subastas getSubastasProductor(

            @Path(value="idusuario")
                    Integer id_subasta

    );

}
