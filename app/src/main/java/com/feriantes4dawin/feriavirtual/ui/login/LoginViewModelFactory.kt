package com.feriantes4dawin.feriavirtual.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.feriantes4dawin.feriavirtual.data.LoginDataSource
import com.feriantes4dawin.feriavirtual.data.repos.LoginRepository

class LoginViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(LoginRepository(LoginDataSource())) as T
        }
        throw IllegalArgumentException("El objeto no es un LoginViewModel")
    }


}