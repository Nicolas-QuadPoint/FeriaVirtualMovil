package com.feriantes4dawin.feriavirtualmovil.ui.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class SimpleTextWatcher implements TextWatcher {

    protected EditText txt;

    public SimpleTextWatcher(EditText txt){
        this.txt = txt;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after){

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count){

    }

    @Override
    public void afterTextChanged(Editable e){

    }

}
