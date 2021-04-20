package com.feriantes4dawin.feriavirtualmovil.data.services;

import com.feriantes4dawin.feriavirtualmovil.data.network.responses.UsuarioResponse;
import kotlinx.coroutines.Deferred;
import retrofit2.http.*;

public interface UsuarioAPIService {

    @FormUrlEncoded
    @POST("auth/login")
    public UsuarioResponse login(

        @Field(value="email")
        String email,

        @Field(value="contrasena")
        String contrasena

    );

    @GET("usuario/{idusuario}")
    public UsuarioResponse getUserInfo(

            @Path(value="idusuario")
            Integer personalID

    );

}