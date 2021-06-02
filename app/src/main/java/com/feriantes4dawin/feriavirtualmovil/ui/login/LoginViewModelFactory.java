package com.feriantes4dawin.feriavirtualmovil.ui.login;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.feriantes4dawin.feriavirtualmovil.FeriaVirtualApplication;
import com.feriantes4dawin.feriavirtualmovil.data.repos.UsuarioRepository;
import com.google.gson.Gson;

public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private UsuarioRepository usuarioRepository;
    private Gson convertidorJSON;
    private FeriaVirtualApplication fva;

    public LoginViewModelFactory(UsuarioRepository usuarioRepository, Gson convertidorJSON, FeriaVirtualApplication fva){
        super();
        this.usuarioRepository = usuarioRepository;
        this.convertidorJSON = convertidorJSON;
        this.fva = fva;
    }

    @Override
    public  <T extends ViewModel> T create(Class<T> modelClass){
        if(modelClass.isAssignableFrom(LoginViewModel.class)){
            return (T)(new LoginViewModel(usuarioRepository,convertidorJSON,fva) );
        }
        throw new IllegalArgumentException("El objeto no es un LoginViewModel");
    }

}