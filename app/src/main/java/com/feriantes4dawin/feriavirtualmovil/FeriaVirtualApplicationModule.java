package com.feriantes4dawin.feriavirtualmovil;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FeriaVirtualApplicationModule {

    private Application application;

    @Inject
    public FeriaVirtualApplicationModule(Application app){

        this.application = app;
    }


    @Provides
    @Singleton
    public Application getContext(){
        return application;
    }

}
