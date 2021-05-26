package com.feriantes4dawin.feriavirtualmovil.data.repos;

import androidx.lifecycle.LiveData;

import com.feriantes4dawin.feriavirtualmovil.data.Result;
import com.feriantes4dawin.feriavirtualmovil.data.db.VentaDAO;
import com.feriantes4dawin.feriavirtualmovil.data.models.Usuario;
import com.feriantes4dawin.feriavirtualmovil.data.models.Ventas;
import com.feriantes4dawin.feriavirtualmovil.data.network.VentaAPIService;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class VentaRepositoryImpl implements VentaRepository {

    public VentaDAO ventaDAO;
    public VentaAPIService ventaAPI;

    @Inject
    public VentaRepositoryImpl(VentaDAO ventaDAO,VentaAPIService ventaAPI){
        this.ventaDAO = ventaDAO;
        this.ventaAPI = ventaAPI;
    }

    @Override
    @Provides
    public VentaRepository getInstance(){
        return this;
    }

    @Override
    public Result<LiveData<Ventas>> getVentasDisponibles(Usuario usuario) {
        return null;
    }


}
