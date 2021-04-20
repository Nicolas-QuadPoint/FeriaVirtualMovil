package com.feriantes4dawin.feriavirtualmovil.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.feriantes4dawin.feriavirtualmovil.data.LoginDataSource
import com.feriantes4dawin.feriavirtual.data.repos.LoginRepository

class LoginViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(LoginRepository(_root_ide_package_.com.feriantes4dawin.feriavirtualmovil.data.LoginDataSource())) as T
        }
        throw IllegalArgumentException("El objeto no es un LoginViewModel")
    }


}