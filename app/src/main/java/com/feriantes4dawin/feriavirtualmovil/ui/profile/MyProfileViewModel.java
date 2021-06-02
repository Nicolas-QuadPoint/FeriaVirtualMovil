package com.feriantes4dawin.feriavirtualmovil.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.feriantes4dawin.feriavirtualmovil.FeriaVirtualApplication;
import com.feriantes4dawin.feriavirtualmovil.data.models.ObjetoModificacionContrasena;
import com.feriantes4dawin.feriavirtualmovil.data.models.ResultadoID;
import com.feriantes4dawin.feriavirtualmovil.data.models.ResultadoUsuario;
import com.feriantes4dawin.feriavirtualmovil.data.models.Usuario;
import com.feriantes4dawin.feriavirtualmovil.data.repos.UsuarioRepository;
import com.feriantes4dawin.feriavirtualmovil.ui.util.FeriaVirtualConstants;
import com.feriantes4dawin.feriavirtualmovil.ui.util.SimpleAction;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfileViewModel extends ViewModel {

    private UsuarioRepository usuarioRepository;
    private Gson convertidorJSON;
    private FeriaVirtualApplication fva;

    private MutableLiveData<Usuario> datosMutablesUsuario;
    private LiveData<Usuario> datosUsuario;

    public MyProfileViewModel(UsuarioRepository ur, Gson convertidorJSON, FeriaVirtualApplication fva) {

        this.usuarioRepository = ur;
        this.convertidorJSON = convertidorJSON;
        this.fva = fva;
        this.datosMutablesUsuario = new MutableLiveData<Usuario>();
        this.datosUsuario = datosMutablesUsuario;

    }

    public LiveData<Usuario> getDatosUsuario() {

        /**
         * Ejecutara una rutina asíncrona, donde dependiendo del
         * dato a obtener, se podrá actualizar los datos de usuario
         * en la vista del fragmento o no.
         */
        //if(datosMutablesUsuario.getValue() == null){
        cargarDatosUsuario();
        //}
        return datosUsuario;
    }

    private void cargarDatosUsuario() {

        try {

            Usuario u;
            String usuarioString;
            Call<ResultadoUsuario> ruc;
            SharedPreferences sp = fva.getSharedPreferences(
                    FeriaVirtualConstants.FERIAVIRTUAL_MOVIL_SHARED_PREFERENCES,
                    Context.MODE_PRIVATE
            );

            usuarioString = sp.getString(FeriaVirtualConstants.SP_USUARIO_OBJ_STR, "");
            u = convertidorJSON.fromJson(usuarioString,Usuario.class);
            ruc = this.usuarioRepository.getInfoUsuario(u);

            if(ruc != null){
                ruc.enqueue(new Callback<ResultadoUsuario>() {
                    @Override
                    public void onResponse(Call<ResultadoUsuario> call, Response<ResultadoUsuario> response) {

                        //Establecemos los datos recuperados de usuario aquí. Si en
                        //MyProfileFragment están obesrvando el objeto 'LiveData' asociado a
                        //datosMutablesUsuario, entonces el evento onChange se activará, y podremos
                        //mostrar datos al usuario
                        datosMutablesUsuario.setValue(response.body().usuario);

                    }

                    @Override
                    public void onFailure(Call<ResultadoUsuario> call, Throwable t) {
                        Log.e("MY_PROFILE_VIEW_MODEL", "Error! no se pudo recuperar datos de usuario!: " + t.toString());
                        datosMutablesUsuario.setValue(null);
                    }
                });

            } else {

                Log.e("MY_PROFILE_VIEW_MODEL", "Error! no se pudo recuperar datos de usuario!: Objeto respuesta no pudo obtenerse ");
                datosMutablesUsuario.setValue(null);

            }


        } catch(Exception ex) {
            Log.e("MY_PROFILE_VIEW_MODEL", String.format("Ocurrio un error!!!: %s",ex.toString()) );
            datosMutablesUsuario.setValue(null);
        }

    }

    public void guardarCambiosUsuario(Usuario u){

    }

    public void cambiarContrasena(ObjetoModificacionContrasena omc, SimpleAction simpleAction) {

        Call<ResultadoID> ruc = usuarioRepository.changePasswordUsuario(omc);

        if(simpleAction == null) {
            return;
        }

        if(ruc != null){

            ruc.enqueue(new Callback<ResultadoID>() {
                @Override
                public void onResponse(Call<ResultadoID> call, Response<ResultadoID> response) {

                    Log.i("MY_PROFILE_VIEW_MODEL",String.format("Código de respuesta: %d",response.code()));
                    Boolean resultadofinal;

                    if(response.isSuccessful()){
                        resultadofinal = new Boolean(response.body().id_resultado == 1);
                    } else {
                        resultadofinal = new Boolean(false);
                    }

                    simpleAction.doAction(resultadofinal);

                }

                @Override
                public void onFailure(Call<ResultadoID> call, Throwable t) {
                    simpleAction.doAction(Boolean.valueOf(false));
                }
            });

        } else {

            simpleAction.doAction(Boolean.valueOf(false));
        }

    }
}

