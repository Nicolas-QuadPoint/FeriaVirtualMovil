package com.feriantes4dawin.feriavirtual.data.network

import androidx.lifecycle.LiveData
import com.feriantes4dawin.feriavirtual.data.network.responses.UsuarioResponse

interface UsuarioDataSource {

    var usuarioDescargado : LiveData<UsuarioResponse>

    suspend fun fetchDataUsuario(email:String,contrasena:String)
}