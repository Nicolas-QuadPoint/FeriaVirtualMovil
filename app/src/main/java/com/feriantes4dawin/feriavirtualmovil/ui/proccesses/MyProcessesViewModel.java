package com.feriantes4dawin.feriavirtualmovil.ui.proccesses;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyProcessesViewModel extends ViewModel {

    public  MutableLiveData<String> _text;
    public LiveData<String> text;

    public MyProcessesViewModel(){

        this._text = new MutableLiveData<String>();
        this._text.setValue("This is proccess Fragment!");
        this.text = _text;
    }
}