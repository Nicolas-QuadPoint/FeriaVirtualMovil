package com.feriantes4dawin.feriavirtualmovil.data.network;

import androidx.lifecycle.LiveData;
import com.feriantes4dawin.feriavirtualmovil.data.network.responses.UsuarioResponse;

public interface UsuarioDataSource {

    public LiveData<UsuarioResponse> usuarioDescargado = null;

    public void fetchDataUsuario(String email,String contrasena);
}