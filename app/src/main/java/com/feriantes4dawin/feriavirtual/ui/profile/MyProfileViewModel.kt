package com.feriantes4dawin.feriavirtual.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.feriantes4dawin.feriavirtual.data.repos.UsuarioRepository

class MyProfileViewModel constructor(var usuarioRepo:UsuarioRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is profile Fragment"
    }

    val text: LiveData<String> = _text

}