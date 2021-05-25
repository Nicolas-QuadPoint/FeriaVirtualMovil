package com.feriantes4dawin.feriavirtualmovil.data.network;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.feriantes4dawin.feriavirtualmovil.data.models.ResultadoUsuario;
import com.feriantes4dawin.feriavirtualmovil.data.services.UsuarioAPIService;

public class UsuarioDataSourceImpl implements UsuarioDataSource{

    private UsuarioAPIService usuService;
    private MutableLiveData<ResultadoUsuario> _usuarioDescargado;
    public LiveData<ResultadoUsuario> usuarioDescargado;

    public UsuarioDataSourceImpl(UsuarioAPIService api){
        this.usuService = api;
        this._usuarioDescargado = new MutableLiveData<ResultadoUsuario>();
        this.usuarioDescargado = _usuarioDescargado;
    }

    @Override
    public void fetchDataUsuario(String email,String contrasena) {

        try {

            ResultadoUsuario datos = usuService.login(email,contrasena);
            _usuarioDescargado.postValue(datos);


        } catch (Exception ex){

            Log.e("[USUARIO-DATA-SOURCE]","Problemas en la conexión a internet: ${ex.message}");

        }

    }
}