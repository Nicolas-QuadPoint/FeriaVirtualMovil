package com.feriantes4dawin.feriavirtualmovil.data.repos;

import androidx.lifecycle.LiveData;
import com.feriantes4dawin.feriavirtualmovil.data.models.Usuario;

public interface UsuarioRepository {
    boolean loginUsuario(String email,String contrasena);
    LiveData<Usuario> getInfoUsuario(Usuario u);
}