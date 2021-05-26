package com.feriantes4dawin.feriavirtualmovil;

import com.feriantes4dawin.feriavirtualmovil.data.datasources.local.FeriaVirtualDBProvider;
import com.feriantes4dawin.feriavirtualmovil.data.datasources.remote.FeriaVirtualAPIProvider;
import com.feriantes4dawin.feriavirtualmovil.data.repos.SubastaRepository;
import com.feriantes4dawin.feriavirtualmovil.data.repos.SubastaRepositoryImpl;
import com.feriantes4dawin.feriavirtualmovil.data.repos.UsuarioRepository;
import com.feriantes4dawin.feriavirtualmovil.data.repos.UsuarioRepositoryImpl;
import com.feriantes4dawin.feriavirtualmovil.data.repos.VentaRepository;
import com.feriantes4dawin.feriavirtualmovil.data.repos.VentaRepositoryImpl;
import com.feriantes4dawin.feriavirtualmovil.ui.profile.MyProfileFragment;
import com.feriantes4dawin.feriavirtualmovil.ui.sales.SaleDetailActivity;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

@Component(
        modules={
                FeriaVirtualAPIProvider.class,
                FeriaVirtualDBProvider.class,
                UsuarioRepositoryImpl.class,
                VentaRepositoryImpl.class,
                SubastaRepositoryImpl.class
        })
public interface FeriaVirtualComponent {

    void injectUsuarioRepositoryIntoMyProfileFragmentt(MyProfileFragment mpf);
    void injectVentaRepositoryIntoSaleDetailActivity(SaleDetailActivity sda);
    void injectSubastaRepositoryIntoSaleDetailActivity(SaleDetailActivity sda);

}
