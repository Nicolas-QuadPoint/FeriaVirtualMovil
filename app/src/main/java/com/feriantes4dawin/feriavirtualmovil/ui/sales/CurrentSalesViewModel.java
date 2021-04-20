package com.feriantes4dawin.feriavirtualmovil.ui.sales;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class CurrentSalesViewModel extends ViewModel {

    private SavedStateHandle savedState;
    private String text;

    public CurrentSalesViewModel(SavedStateHandle ss){
        this.savedState = ss;
        this.text = "nada";
    }
}