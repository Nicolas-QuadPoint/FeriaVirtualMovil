package com.feriantes4dawin.feriavirtualmovil.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.feriantes4dawin.feriavirtualmovil.data.repos.UsuarioRepository;

public class MyProfileViewModel extends ViewModel {

    private UsuarioRepository usuarioRepo;
    private MutableLiveData<String> _text;
    private LiveData<String> text;

    public MyProfileViewModel(UsuarioRepository ur){

        this.usuarioRepo = ur;
        this._text =  new MutableLiveData<String>();
        this._text.setValue("This is my profile fragment");
        this.text = _text;

    }

}