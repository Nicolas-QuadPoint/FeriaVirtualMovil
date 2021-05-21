package com.feriantes4dawin.feriavirtualmovil.data.services;

import com.feriantes4dawin.feriavirtualmovil.data.network.responses.UsuarioResponse;
import retrofit2.http.*;

public interface UsuarioAPIService {

    @FormUrlEncoded
    @POST("auth/login")
    UsuarioResponse login(

        @Field(value="email")
        String email,

        @Field(value="contrasena")
        String contrasena

    );

    @GET("usuario/{idusuario}")
    UsuarioResponse getUserInfo(

            @Path(value="idusuario")
            Integer personalID

    );

}