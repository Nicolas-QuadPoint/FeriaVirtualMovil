package com.feriantes4dawin.feriavirtual.data.services

import com.feriantes4dawin.feriavirtual.data.network.responses.UsuarioResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface UsuarioAPIService {

    @FormUrlEncoded
    @POST("auth/login")
    fun login(

        @Field(value="email")
        email:String,

        @Field(value="contrasena")
        contrasena:String

    ): Deferred<UsuarioResponse>

    @GET("usuario/{idusuario}")
    fun getUserInfo(

            @Path(value="idusuario")
            personalID:Int

    ): Deferred<UsuarioResponse>

}