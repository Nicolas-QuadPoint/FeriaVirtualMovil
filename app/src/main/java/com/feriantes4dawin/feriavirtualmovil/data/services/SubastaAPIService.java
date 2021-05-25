package com.feriantes4dawin.feriavirtualmovil.data.services;

import com.feriantes4dawin.feriavirtualmovil.data.models.ObjetoPujaSubastaProductor;
import com.feriantes4dawin.feriavirtualmovil.data.models.ObjetoPujaSubastaTransportista;
import com.feriantes4dawin.feriavirtualmovil.data.models.ResultadoID;
import com.feriantes4dawin.feriavirtualmovil.data.models.Subastas;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SubastaAPIService {

    @POST("subasta/{idsubasta}/productor/pujar")
    ResultadoID pujarSubastaProductor(

            @Field(value="idsubasta")
                Integer id_subasta,
            ObjetoPujaSubastaProductor puja

    );

    @POST("subasta/{idsubasta}/transportista/pujar")
    ResultadoID pujarSubastaTransportista(

            @Field(value="idsubasta")
                    Integer id_subasta,
            ObjetoPujaSubastaTransportista puja

    );

    @GET("subasta/{idsubasta}/productor/allpujas")
    List<ObjetoPujaSubastaProductor> getAllPujasSubastaProductor(
            @Field(value="idsubasta")
                    Integer id_subasta
    );

    @GET("subasta/productor/{idusuario}")
    Subastas getSubastasProductor(

            @Path(value="idusuario")
                Integer id_usuario

    );

}
