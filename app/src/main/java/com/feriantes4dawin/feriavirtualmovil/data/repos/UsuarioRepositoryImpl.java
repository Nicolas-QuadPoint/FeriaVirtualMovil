package com.feriantes4dawin.feriavirtualmovil.data.repos;

import androidx.lifecycle.LiveData;

import com.feriantes4dawin.feriavirtualmovil.data.Result;
import com.feriantes4dawin.feriavirtualmovil.data.db.UsuarioDAO;
import com.feriantes4dawin.feriavirtualmovil.data.models.Usuario;
import com.feriantes4dawin.feriavirtualmovil.data.network.UsuarioAPIService;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private UsuarioRepository instancia;

    /* Para conexiones locales */
    private UsuarioDAO usuarioDAO;

    /* Para conectarse a la web api */
    private UsuarioAPIService usuarioAPI;

    /* Guarda al usuario activo */
    private Usuario usuarioActivo;

    @Inject
    public UsuarioRepositoryImpl(UsuarioDAO usuarioDAO,UsuarioAPIService usuarioAPI) {

        this.usuarioDAO = usuarioDAO;
        this.usuarioAPI = usuarioAPI;
        /*
        this.usuarioDataSource = uds;

        if(uds != null){

            uds.usuarioDescargado.observeForever(new Observer<ResultadoUsuario>() {
                @Override
                public void onChanged(ResultadoUsuario resultadoUsuario) {
                    saveUsuarioToDatabase(resultadoUsuario.usuario);
                }
            });

        }
         */
    }

    @Provides
    @Override
    public UsuarioRepository getInstance(){
        return this;
    }

    @Override
    public Result<LiveData<Usuario>> loginUsuario(String email, String contrasena) {

        LiveData<Usuario> u = usuarioDAO.find();
        Result<LiveData<Usuario>> resultado = new Result<LiveData<Usuario>>();

        try {

            //Revisando desde el webapi (EN PROGRESO)
            if(false){
                throw new Exception("TODO: no implementado!");
            }
            //Revisando los datos en cache si no hay conexion a internet!
            else if((u.getValue() != null) && (u.getValue().email.equals(email)) && (u.getValue().contrasena.equals(contrasena))){

                return resultado.new Success<LiveData<Usuario>>(u);

            } else {

                throw new Exception("TODO: No implementado!");

            }

        } catch(Exception ex){

            return resultado.new Error<LiveData<Usuario>>(ex);

        }

    }

    @Override
    public Result<LiveData<Usuario>> getInfoUsuario(Usuario usuario) {

        Result<LiveData<Usuario>> resultado = new Result<>();

        return resultado.new Error<>( new Exception("TODO: No implementado!"));
    }

    @Override
    public boolean updateUsuario(int ntelefono,String ndireccion,String npasswd){
        return false;
    }

    private void saveUsuarioToDatabase(Usuario usuario){

        //Insertamos el usuario recuperado de login o getInfoUsuario
        //a la base de datos!
        usuarioDAO.upsert(usuario);
        setLoggedInUser(usuario);

    }

    private void setLoggedInUser(Usuario usuario) {
        this.usuarioActivo = usuario;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}