package com.feriantes4dawin.feriavirtualmovil.ui.proccesses;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.feriantes4dawin.feriavirtualmovil.FeriaVirtualApplication;
import com.feriantes4dawin.feriavirtualmovil.data.models.VentasSimples;
import com.feriantes4dawin.feriavirtualmovil.data.repos.VentaRepository;

public class MyProcessesViewModel extends ViewModel {

    private FeriaVirtualApplication feriaVirtualApplication;
    private VentaRepository ventaRepository;

    private LiveData<VentasSimples> datosVenta;
    private MutableLiveData<VentasSimples> datosMutablesVenta;

    public MyProcessesViewModel(VentaRepository ventaRepository, FeriaVirtualApplication feriaVirtualApplication){

        this.feriaVirtualApplication = feriaVirtualApplication;
        this.ventaRepository = ventaRepository;

        this.datosMutablesVenta = new MutableLiveData<>();
        this.datosVenta = datosMutablesVenta;

    }

    LiveData<VentasSimples> getDatosVenta(){

        datosMutablesVenta.setValue(null);
        return datosVenta;
    }


}