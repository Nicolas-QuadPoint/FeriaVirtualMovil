package com.feriantes4dawin.feriavirtual.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.feriantes4dawin.feriavirtual.data.network.responses.UsuarioResponse
import com.feriantes4dawin.feriavirtual.data.services.UsuarioAPIService
import com.feriantes4dawin.feriavirtual.misc.exceptions.NoConnectionException

class UsuarioDataSourceImpl(
        private var usuService: UsuarioAPIService
): UsuarioDataSource {

    private var _usuarioDescargado = MutableLiveData<UsuarioResponse>()
    override var usuarioDescargado: LiveData<UsuarioResponse>
        get() = _usuarioDescargado
        set(value) { _usuarioDescargado = value as MutableLiveData<UsuarioResponse>}

    override suspend fun fetchDataUsuario(email: String, contrasena: String) {

        try {

            val datos = usuService.login(email,contrasena).await()
            _usuarioDescargado.postValue(datos)


        } catch (ex: NoConnectionException){

            Log.e("[USUARIO-DATA-SOURCE]","Problemas en la conexión a internet: ${ex.message}")

        }

    }
}