package com.feriantes4dawin.feriavirtualmovil.data.datasources.local;

import android.content.Context;

import com.feriantes4dawin.feriavirtualmovil.data.db.SubastaDAO;

import javax.inject.Inject;

public class SubastaLocalDataSource {

    public SubastaDAO subastaDAO;

    @Inject
    public SubastaLocalDataSource(Context c){

        this.subastaDAO = new FeriaVirtualDBProvider().provideDB(c).getSubastaDAO();

    }

}
