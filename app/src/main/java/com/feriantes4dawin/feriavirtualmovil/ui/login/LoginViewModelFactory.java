package com.feriantes4dawin.feriavirtualmovil.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.feriantes4dawin.feriavirtualmovil.data.LoginDataSource;
import com.feriantes4dawin.feriavirtualmovil.data.repos.LoginRepository;

public class LoginViewModelFactory implements ViewModelProvider.Factory {

    @Override
    public  <T extends ViewModel> T create(Class<T> modelClass){
        if(modelClass.isAssignableFrom(LoginViewModel.class)){
            return (T)(new LoginViewModel(new LoginRepository(new LoginDataSource())));
        }
        throw new IllegalArgumentException("El objeto no es un LoginViewModel");
    }

}