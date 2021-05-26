package com.feriantes4dawin.feriavirtualmovil.data.datasources.local;

import android.content.Context;

import com.feriantes4dawin.feriavirtualmovil.data.db.UsuarioDAO;

import javax.inject.Inject;

public class UsuarioLocalDataSource {

    public UsuarioDAO usuarioDAO;

    @Inject
    public UsuarioLocalDataSource(Context c){

        this.usuarioDAO = new FeriaVirtualDBProvider().provideDB(c).getUsuarioDAO();

    }
}
