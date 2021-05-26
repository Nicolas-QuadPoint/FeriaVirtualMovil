package com.feriantes4dawin.feriavirtualmovil.data.repos;

import androidx.lifecycle.LiveData;

import com.feriantes4dawin.feriavirtualmovil.data.Result;
import com.feriantes4dawin.feriavirtualmovil.data.models.Usuario;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;


public interface UsuarioRepository {

    UsuarioRepository getInstance();

    Result<LiveData<Usuario>> loginUsuario(String email, String contrasena);

    Result<LiveData<Usuario>> getInfoUsuario(Usuario u);

    boolean updateUsuario(int ntelefono,String ndireccion,String npasswd);
}