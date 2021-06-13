package com.feriantes4dawin.feriavirtualmovil.data.repos;

import com.feriantes4dawin.feriavirtualmovil.data.db.SubastaDAO;
import com.feriantes4dawin.feriavirtualmovil.data.network.SubastaAPIService;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class SubastaRepositoryImpl implements SubastaRepository {

    public SubastaDAO subastaDAO;
    public SubastaAPIService subastaAPI;


    @Inject
    public SubastaRepositoryImpl(SubastaDAO subastaDAO,SubastaAPIService subastaAPI){

        this.subastaDAO = subastaDAO;
        this.subastaAPI = subastaAPI;

    }

    /*
    @Override
    @Provides
    public SubastaRepository getInstance(){
        return this;
    }
    */

}
