package com.feriantes4dawin.feriavirtualmovil.ui.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class JsonConverterProvider {

    @Singleton
    @Provides
    public Gson getJSONParser(){

        return new GsonBuilder().enableComplexMapKeySerialization().serializeNulls().create();

    }

}
