package com.feriantes4dawin.feriavirtualmovil.data.datasources.local;

import android.content.Context;
import com.feriantes4dawin.feriavirtualmovil.data.db.FeriaVirtualDatabase;
import com.feriantes4dawin.feriavirtualmovil.data.db.FeriaVirtualDatabase;
import com.feriantes4dawin.feriavirtualmovil.data.db.SubastaDAO;
import com.feriantes4dawin.feriavirtualmovil.data.db.UsuarioDAO;
import com.feriantes4dawin.feriavirtualmovil.data.db.VentaDAO;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FeriaVirtualDBProvider {

    @Provides
    @Singleton
    public FeriaVirtualDatabase provideDB(Context c){
        return FeriaVirtualDatabase.getInstance(c);
    }

    @Provides
    @Singleton
    public UsuarioDAO provideUsuarioDAO(Context c){
        return FeriaVirtualDatabase.getInstance(c).getUsuarioDAO();
    }

    @Provides
    @Singleton
    public VentaDAO provideVentaDAO(Context c){
        return FeriaVirtualDatabase.getInstance(c).getVentaDAO();
    }

    @Provides
    @Singleton
    public SubastaDAO provideSubastaDAO(Context c){
        return FeriaVirtualDatabase.getInstance(c).getSubastaDAO();
    }



}