package com.feriantes4dawin.feriavirtualmovil.data.network;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.feriantes4dawin.feriavirtualmovil.data.network.responses.UsuarioResponse;
import com.feriantes4dawin.feriavirtualmovil.data.services.UsuarioAPIService;
import com.feriantes4dawin.feriavirtualmovil.misc.exceptions.NoConnectionException;

import java.io.IOException;

public class UsuarioDataSourceImpl implements UsuarioDataSource{

    private UsuarioAPIService usuService;
    private MutableLiveData<UsuarioResponse> _usuarioDescargado;
    public LiveData<UsuarioResponse> usuarioDescargado;

    public UsuarioDataSourceImpl(UsuarioAPIService api){
        this.usuService = api;
        this._usuarioDescargado = new MutableLiveData<UsuarioResponse>();
        this.usuarioDescargado = _usuarioDescargado;
    }

    @Override
    public void fetchDataUsuario(String email,String contrasena) {

        try {

            UsuarioResponse datos = usuService.login(email,contrasena);
            _usuarioDescargado.postValue(datos);


        } catch (Exception ex){

            Log.e("[USUARIO-DATA-SOURCE]","Problemas en la conexión a internet: ${ex.message}");

        }

    }
}