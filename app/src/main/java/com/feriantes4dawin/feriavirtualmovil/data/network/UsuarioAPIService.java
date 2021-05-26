package com.feriantes4dawin.feriavirtualmovil.data.network;

import com.feriantes4dawin.feriavirtualmovil.data.models.ResultadoID;
import com.feriantes4dawin.feriavirtualmovil.data.models.ResultadoUsuario;
import com.feriantes4dawin.feriavirtualmovil.data.models.Usuario;

import retrofit2.http.*;

public interface UsuarioAPIService {

    @FormUrlEncoded
    @POST("auth/login")
    ResultadoUsuario login(

        @Field(value="email")
        String email,

        @Field(value="contrasena")
        String contrasena

    );

    @GET("usuario/{idusuario}")
    ResultadoUsuario getInfoUsuario(

            @Path(value="idusuario")
            Integer personalID

    );

    @POST("usuario/{idusuario}/update")
    ResultadoID updateUsuario(

            @Path(value="idusuario")
                    Integer personalID,
            Usuario valoresNuevos

    );

}