package com.feriantes4dawin.feriavirtualmovil.ui.sales;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.feriantes4dawin.feriavirtualmovil.FeriaVirtualApplication;
import com.feriantes4dawin.feriavirtualmovil.data.models.Venta;
import com.feriantes4dawin.feriavirtualmovil.data.repos.VentaRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaleDetailViewModel extends ViewModel {


    VentaRepository ventaRepository;
    FeriaVirtualApplication feriaVirtualApplication;

    LiveData<Venta> datosVenta;
    MutableLiveData<Venta> datosMutablesVenta;


    public SaleDetailViewModel(VentaRepository ventaRepository, FeriaVirtualApplication feriaVirtualApplication){

        this.ventaRepository = ventaRepository;
        this.feriaVirtualApplication = feriaVirtualApplication;

        this.datosMutablesVenta = new MutableLiveData<Venta>();
        this.datosVenta = datosMutablesVenta;

    }

    public LiveData<Venta> getDatosVenta(Integer venta_id){

        cargarDatosVenta(venta_id);

        return datosVenta;

    }

    private void cargarDatosVenta(Integer venta_id){

        if(venta_id != null){

            Call<Venta> ventaCall = ventaRepository.getInfoVenta(venta_id);
            ventaCall.enqueue(new Callback<Venta>() {
                @Override
                public void onResponse(Call<Venta> call, Response<Venta> response) {

                    if(response.isSuccessful() && response.body() != null) {

                        datosMutablesVenta.setValue(response.body());

                    } else {

                        datosMutablesVenta.setValue(null);

                    }

                }

                @Override
                public void onFailure(Call<Venta> call, Throwable t) {
                    Log.e("SALE_DETAIL_VIEW_MODEL", "Error! no se pudo recuperar datos de venta!: " + t.toString());
                    datosMutablesVenta.setValue(null);
                }
            });

        } else{

            datosMutablesVenta.setValue(null);

        }

    }

}