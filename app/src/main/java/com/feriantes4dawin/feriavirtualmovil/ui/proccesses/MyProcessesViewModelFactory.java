package com.feriantes4dawin.feriavirtualmovil.ui.proccesses;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.feriantes4dawin.feriavirtualmovil.FeriaVirtualApplication;
import com.feriantes4dawin.feriavirtualmovil.data.models.VentasSimples;
import com.feriantes4dawin.feriavirtualmovil.data.repos.VentaRepository;
import com.feriantes4dawin.feriavirtualmovil.ui.profile.MyProfileViewModel;

import org.jetbrains.annotations.NotNull;

public class MyProcessesViewModelFactory implements ViewModelProvider.Factory {

    private FeriaVirtualApplication feriaVirtualApplication;
    private VentaRepository ventaRepository;

    public MyProcessesViewModelFactory(VentaRepository ventaRepository, FeriaVirtualApplication feriaVirtualApplication){

        this.feriaVirtualApplication = feriaVirtualApplication;
        this.ventaRepository = ventaRepository;

    }

    @NonNull
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {

        if(modelClass.isAssignableFrom(MyProcessesViewModel.class)) {

            return (T)(new MyProcessesViewModel(ventaRepository,feriaVirtualApplication));

        }

        throw new IllegalArgumentException("No es un objeto MyProcessesViewModel");
    }
}
