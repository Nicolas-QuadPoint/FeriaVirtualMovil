package com.feriantes4dawin.feriavirtualmovil.data.network;

import com.feriantes4dawin.feriavirtualmovil.data.models.LoginObject;
import com.feriantes4dawin.feriavirtualmovil.data.models.ObjetoModificacionContrasena;
import com.feriantes4dawin.feriavirtualmovil.data.models.ResultadoID;
import com.feriantes4dawin.feriavirtualmovil.data.models.ResultadoUsuario;
import com.feriantes4dawin.feriavirtualmovil.data.models.Usuario;

import retrofit2.Call;
import retrofit2.http.*;

public interface UsuarioAPIService {

    @POST("auth/login")
    Call<ResultadoUsuario> login(
        @Body
        LoginObject lo

    );

    @GET("usuario/{idusuario}")
    Call<ResultadoUsuario> getInfoUsuario(

            @Path(value="idusuario")
            Integer personalID

    );

    @POST("usuario/{idusuario}/update")
    Call<ResultadoID> updateUsuario(

            @Path(value="idusuario")
                    Integer personalID,
            @Body
            Usuario valoresNuevos

    );

    @POST("usuario/{idusuario}/changepassword")
    Call<ResultadoID> changePasswordUsuario(

            @Path(value="idusuario")
                    Integer personalID,
            @Body
            ObjetoModificacionContrasena omc

    );

}