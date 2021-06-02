package com.feriantes4dawin.feriavirtualmovil.ui.sales;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.feriantes4dawin.feriavirtualmovil.FeriaVirtualApplication;
import com.feriantes4dawin.feriavirtualmovil.data.models.VentaSimple;
import com.feriantes4dawin.feriavirtualmovil.data.models.Usuario;
import com.feriantes4dawin.feriavirtualmovil.data.models.VentasSimples;
import com.feriantes4dawin.feriavirtualmovil.data.repos.VentaRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentSalesViewModel extends ViewModel {

    private VentaRepository ventaRepository;
    private FeriaVirtualApplication fva;

    private LiveData<VentasSimples> datosVenta;
    private MutableLiveData<VentasSimples> datosMutablesVenta;


    public CurrentSalesViewModel(VentaRepository ventaRepository, FeriaVirtualApplication fva){
        this.ventaRepository = ventaRepository;
        this.fva = fva;
        this.datosMutablesVenta = new MutableLiveData<VentasSimples>();
        this.datosVenta = datosMutablesVenta;
    }

    public LiveData<VentasSimples> getDatosVenta(){

        cargarListaVentas();

        return datosVenta;

    }


    private void cargarListaVentas(){

        Usuario u;
        Call<VentasSimples> ruc;
        try{

            u = new Usuario();
            u.id_usuario = 0;
            ruc = ventaRepository.getVentasSimplesDisponibles(u);

            ruc.enqueue(new Callback<VentasSimples>() {
                @Override
                public void onResponse(Call<VentasSimples> call, Response<VentasSimples> response) {

                    Log.e("CUR_SALES_VIEW_MODEL",String.format("Código de respuesta http: %d",response.code()));

                    if(response.isSuccessful() && response.body().ventas != null && response.body().ventas.size() > 0){

                        datosMutablesVenta.setValue(response.body());

                    } else {

                        datosMutablesVenta.setValue(null);

                    }

                }

                @Override
                public void onFailure(Call<VentasSimples> call, Throwable t) {

                    Log.e("CUR_SALES_VIEW_MODEL",String.format("No se pudo recuperar datos de venta!: %s",t.toString()));
                    datosMutablesVenta.setValue(null);

                }
            });



        } catch(Exception ex){

            Log.e("CUR_SALES_VIEW_MODEL",String.format("No se pudo recuperar datos de venta!: %s",ex.toString()));
            datosMutablesVenta.setValue(null);

        }

    }


}