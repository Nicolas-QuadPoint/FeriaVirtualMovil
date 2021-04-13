package com.feriantes4dawin.feriavirtual.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.feriantes4dawin.feriavirtual.data.repos.UsuarioRepository

class MyProfileViewModelFactory(private var usuarioRepo: UsuarioRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        @Suppress("UNCHECKED_CAST")
        if(modelClass.isAssignableFrom(MyProfileViewModel::class.java)) {

            return MyProfileViewModel(usuarioRepo) as T

        }

        throw IllegalArgumentException("No es un objeto MyProfileViewModel")
    }

}