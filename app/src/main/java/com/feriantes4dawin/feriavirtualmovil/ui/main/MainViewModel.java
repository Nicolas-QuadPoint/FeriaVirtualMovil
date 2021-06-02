package com.feriantes4dawin.feriavirtualmovil.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.feriantes4dawin.feriavirtualmovil.FeriaVirtualApplication;
import com.feriantes4dawin.feriavirtualmovil.data.models.ResultadoUsuario;
import com.feriantes4dawin.feriavirtualmovil.data.models.Usuario;
import com.feriantes4dawin.feriavirtualmovil.data.repos.UsuarioRepository;
import com.feriantes4dawin.feriavirtualmovil.ui.util.FeriaVirtualConstants;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    
    private UsuarioRepository usuarioRepository;
    private Gson convertidorJSON;
    private FeriaVirtualApplication feriaVirtualApplication;
    
    private LiveData<Usuario> datosUsuario;
    private MutableLiveData<Usuario> datosMutablesUsuario;
    
    
    public MainViewModel(UsuarioRepository usuarioRepository, Gson convertidorJSON, FeriaVirtualApplication feriaVirtualApplication){
        
        this.usuarioRepository = usuarioRepository;
        this.convertidorJSON = convertidorJSON;
        this.feriaVirtualApplication = feriaVirtualApplication;

        this.datosMutablesUsuario = new MutableLiveData<Usuario>();
        this.datosUsuario = datosMutablesUsuario;

    }

    public LiveData<Usuario> getDatosUsuario(){

        cargarDatosUsuario();

        return datosUsuario;
    }


    private void cargarDatosUsuario(){

        try{

            SharedPreferences sp;
            String usuarioString;
            Usuario usuario;
            Call<ResultadoUsuario> resultadoUsuarioCall;

            sp = feriaVirtualApplication.getSharedPreferences(
                    FeriaVirtualConstants.FERIAVIRTUAL_MOVIL_SHARED_PREFERENCES,
                    Context.MODE_PRIVATE
            );

            usuarioString = sp.getString(FeriaVirtualConstants.SP_USUARIO_OBJ_STR,"");
            usuario = convertidorJSON.fromJson(usuarioString,Usuario.class);

            resultadoUsuarioCall = usuarioRepository.getInfoUsuario(usuario);

            resultadoUsuarioCall.enqueue(new Callback<ResultadoUsuario>() {

                @Override
                public void onResponse(Call<ResultadoUsuario> call, Response<ResultadoUsuario> response) {

                    Log.e("MAIN_VIEW_MODEL",String.format("Código de respuesta http: %d",response.code()));

                    if(response.isSuccessful()){

                        datosMutablesUsuario.setValue(response.body().usuario);

                    } else {

                        datosMutablesUsuario.setValue(null);

                    }


                }

                @Override
                public void onFailure(Call<ResultadoUsuario> call, Throwable t) {
                    //Ignoramos el error de forma silenciosa... no sin antes decirlo en log
                    Log.e("MAIN_VIEW_MODEL",String.format("No se pudo obtener datos de usuario!: %s",t.toString()));
                    datosMutablesUsuario.setValue(null);
                }

            });

        } catch(Exception ex){

        }

    }
}