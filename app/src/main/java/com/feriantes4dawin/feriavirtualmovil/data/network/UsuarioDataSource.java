package com.feriantes4dawin.feriavirtualmovil.data.network;

import androidx.lifecycle.LiveData;
import com.feriantes4dawin.feriavirtualmovil.data.models.ResultadoUsuario;

public interface UsuarioDataSource {

    LiveData<ResultadoUsuario> usuarioDescargado = null;

    void fetchDataUsuario(String email,String contrasena);
}