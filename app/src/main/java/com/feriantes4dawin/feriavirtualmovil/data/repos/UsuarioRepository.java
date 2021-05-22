package com.feriantes4dawin.feriavirtualmovil.data.repos;

import androidx.lifecycle.LiveData;
import com.feriantes4dawin.feriavirtualmovil.data.db.UsuarioDAO;
import com.feriantes4dawin.feriavirtualmovil.data.model.Usuario;
import com.feriantes4dawin.feriavirtualmovil.data.network.responses.UsuarioResponse;

public interface UsuarioRepository {
    boolean loginUsuario(String email,String contrasena);
    LiveData<Usuario> getInfoUsuario(Usuario u);
}