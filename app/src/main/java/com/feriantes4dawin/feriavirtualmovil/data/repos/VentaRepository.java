package com.feriantes4dawin.feriavirtualmovil.data.repos;

import androidx.lifecycle.LiveData;

import com.feriantes4dawin.feriavirtualmovil.data.Result;
import com.feriantes4dawin.feriavirtualmovil.data.models.Usuario;
import com.feriantes4dawin.feriavirtualmovil.data.models.Ventas;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;


public interface VentaRepository {

    VentaRepository getInstance();
    Result<LiveData<Ventas>> getVentasDisponibles(Usuario usuario);

}
