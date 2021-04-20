package com.feriantes4dawin.feriavirtualmovil.data.repos;

import androidx.lifecycle.LiveData;
import com.feriantes4dawin.feriavirtualmovil.data.db.UsuarioDAO;
import com.feriantes4dawin.feriavirtualmovil.data.model.Usuario;
import com.feriantes4dawin.feriavirtualmovil.data.network.UsuarioDataSource;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    private UsuarioDataSource usuarioDataSource;
    private UsuarioDAO usuarioDAO;

    public UsuarioRepositoryImpl(UsuarioDAO usudao,UsuarioDataSource uds) {

        usuarioDAO = usudao;
        usuarioDataSource = uds;

        /*
        usuarioDataSource.apply {
            usuarioDescargado.observeForever{ nuevoUsuarioResponse ->

                saveUsuarioToDatabase(nuevoUsuarioResponse.usuario)

            }

        }
         */

    }

    @Override
    public boolean loginUsuario(String email, String contrasena) {

        /*
            LiveData<Usuario> u = usuarioDAO.find();
            return@withContext ((u.value != null) and (u.value!!.email == email) and (u.value!!.contrasena == contrasena))

        }

         */
        return false;

    }

    @Override
    public LiveData<Usuario> getInfoUsuario(Usuario u){

        /*
        return withContext(Dispatchers.IO){

            return@withContext usuarioDAO.find()

        }

         */
        return null;
    }

    private void saveUsuarioToDatabase(Usuario u){

        /*
        GlobalScope.launch(Dispatchers.IO){

            //Insertamos el usuario recuperado de login o getInfoUsuario
            //a la base de datos!
            usuarioDAO.upsert(usuario)

        }

         */

    }
}