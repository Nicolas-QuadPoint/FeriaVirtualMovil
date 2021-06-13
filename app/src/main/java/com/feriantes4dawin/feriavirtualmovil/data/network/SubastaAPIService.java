package com.feriantes4dawin.feriavirtualmovil.data.network;

import com.feriantes4dawin.feriavirtualmovil.data.models.ObjetoPujaSubastaProductor;
import com.feriantes4dawin.feriavirtualmovil.data.models.ObjetoPujaSubastaTransportista;
import com.feriantes4dawin.feriavirtualmovil.data.models.ResultadoID;
import com.feriantes4dawin.feriavirtualmovil.data.models.Subastas;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SubastaAPIService {

    @POST("subasta/{idsubasta}/productor/pujar")
    ResultadoID pujarSubastaProductor(

            @Path("idsubasta")
                Integer id_subasta,
            ObjetoPujaSubastaProductor puja

    );

    @POST("subasta/{idsubasta}/transportista/pujar")
    ResultadoID pujarSubastaTransportista(

            @Path("idsubasta")
                Integer id_subasta,
            ObjetoPujaSubastaTransportista puja

    );

    @GET("subasta/{idsubasta}/productor/allpujas")
    List<ObjetoPujaSubastaProductor> getAllPujasSubastaProductor(
            @Path("idsubasta")
                Integer id_subasta
    );

    @GET("subasta/productor/{idusuario}")
    Subastas getSubastasProductor(

            @Path("idusuario")
                Integer id_usuario

    );

}
