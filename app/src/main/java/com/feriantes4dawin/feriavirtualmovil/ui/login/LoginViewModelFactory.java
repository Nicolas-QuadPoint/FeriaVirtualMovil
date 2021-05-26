package com.feriantes4dawin.feriavirtualmovil.ui.login;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.feriantes4dawin.feriavirtualmovil.data.repos.UsuarioRepository;

public class LoginViewModelFactory implements ViewModelProvider.Factory {

    public UsuarioRepository usuarioRepository;


    @Override
    public  <T extends ViewModel> T create(Class<T> modelClass){
        if(modelClass.isAssignableFrom(LoginViewModel.class)){
            return (T)(new LoginViewModel(usuarioRepository) );
        }
        throw new IllegalArgumentException("El objeto no es un LoginViewModel");
    }

}