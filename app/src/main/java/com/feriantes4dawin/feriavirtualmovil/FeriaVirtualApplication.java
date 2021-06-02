package com.feriantes4dawin.feriavirtualmovil;

import android.app.Application;
import android.content.Context;

import com.feriantes4dawin.feriavirtualmovil.data.datasources.local.FeriaVirtualDBProvider;
import com.feriantes4dawin.feriavirtualmovil.data.datasources.remote.FeriaVirtualAPIProvider;

import dagger.Module;
import dagger.Provides;

public class FeriaVirtualApplication extends Application {

    private FeriaVirtualComponent feriaVirtualComponent;

    @Override
    public void onCreate() {

        super.onCreate();
        this.feriaVirtualComponent = DaggerFeriaVirtualComponent.builder().
                feriaVirtualDBProvider(new FeriaVirtualDBProvider(this)).
                feriaVirtualAPIProvider(new FeriaVirtualAPIProvider(this)).
                build();
    }

    public FeriaVirtualComponent getFeriaVirtualComponent(){
        return feriaVirtualComponent;
    }
    //FeriaVirtualApplicationGraphComponent c = DaggerFeriaVirtualApplicationGraph.create();

}
