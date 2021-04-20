package com.feriantes4dawin.feriavirtualmovil.ui.profile;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.feriantes4dawin.feriavirtualmovil.data.repos.UsuarioRepository;

public class MyProfileViewModelFactory implements ViewModelProvider.Factory {

        private UsuarioRepository usuarioRepo;
        public MyProfileViewModelFactory(UsuarioRepository ur){
        this.usuarioRepo = ur;
        }

    public <T extends ViewModel> T create(Class<T> modelClass) {

        if(modelClass.isAssignableFrom(MyProfileViewModel.class)) {

            return (T)(new MyProfileViewModel(usuarioRepo));

        }

        throw new IllegalArgumentException("No es un objeto MyProfileViewModel");
    }

}