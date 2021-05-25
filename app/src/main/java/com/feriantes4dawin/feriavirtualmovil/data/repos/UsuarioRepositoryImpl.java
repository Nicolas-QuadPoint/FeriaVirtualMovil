package com.feriantes4dawin.feriavirtualmovil.data.repos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.feriantes4dawin.feriavirtualmovil.data.db.UsuarioDAO;
import com.feriantes4dawin.feriavirtualmovil.data.network.UsuarioDataSource;
import com.feriantes4dawin.feriavirtualmovil.data.models.ResultadoUsuario;
import com.feriantes4dawin.feriavirtualmovil.data.models.Usuario;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    private UsuarioDataSource usuarioDataSource;
    private UsuarioDAO usuarioDAO;

    public UsuarioRepositoryImpl(UsuarioDAO usudao,UsuarioDataSource uds) {

        usuarioDAO = usudao;
        usuarioDataSource = uds;

        if(uds != null){

            uds.usuarioDescargado.observeForever(new Observer<ResultadoUsuario>() {
                @Override
                public void onChanged(ResultadoUsuario resultadoUsuario) {
                    //saveUsuarioToDatabase(nuevoUsuarioResponse.usuario);
                }
            });

        }
    }

    @Override
    public boolean loginUsuario(String email, String contrasena) {

        LiveData<Usuario> u = usuarioDAO.find();
        return ((u.getValue() != null) && (u.getValue().email.equals(email)) && (u.getValue().contrasena.equals(contrasena)));

    }

    @Override
    public LiveData<Usuario> getInfoUsuario(Usuario u) {
        return null;
    }

    private void saveUsuarioToDatabase(Usuario u){

        //Insertamos el usuario recuperado de login o getInfoUsuario
        //a la base de datos!
        usuarioDAO.upsert(u);


    }
}