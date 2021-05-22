package com.feriantes4dawin.feriavirtualmovil.data.network;

import androidx.lifecycle.LiveData;
import com.feriantes4dawin.feriavirtualmovil.data.network.responses.UsuarioResponse;

public interface UsuarioDataSource {

    LiveData<UsuarioResponse> usuarioDescargado = null;

    void fetchDataUsuario(String email,String contrasena);
}