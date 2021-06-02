package com.feriantes4dawin.feriavirtualmovil.ui.sales;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.feriantes4dawin.feriavirtualmovil.data.repos.VentaRepository;

import com.feriantes4dawin.feriavirtualmovil.FeriaVirtualApplication;

public class CurrentSalesViewModelFactory implements ViewModelProvider.Factory {

    private VentaRepository ventaRepository;
    private FeriaVirtualApplication fva;

    public CurrentSalesViewModelFactory(VentaRepository ventaRepository,FeriaVirtualApplication fva){
        this.ventaRepository = ventaRepository;
        this.fva = fva;
    }

    public <T extends ViewModel> T create(Class<T> modelClass) {

        if(modelClass.isAssignableFrom(CurrentSalesViewModel.class)) {

            return (T)(new CurrentSalesViewModel(ventaRepository,fva));

        }

        throw new IllegalArgumentException("No es un objeto CurrentSalesViewModel");
    }

}