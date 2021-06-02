package com.feriantes4dawin.feriavirtualmovil.ui.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.Patterns;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.feriantes4dawin.feriavirtualmovil.FeriaVirtualApplication;
import com.feriantes4dawin.feriavirtualmovil.R;
import com.feriantes4dawin.feriavirtualmovil.data.models.LoginObject;
import com.feriantes4dawin.feriavirtualmovil.data.models.ResultadoUsuario;
import com.feriantes4dawin.feriavirtualmovil.data.repos.UsuarioRepository;
import com.feriantes4dawin.feriavirtualmovil.ui.util.FeriaVirtualConstants;
import com.google.gson.Gson;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<LoginFormState> _loginForm;

    private UsuarioRepository usuarioRepository;
    private Gson convertidorJSON;
    private FeriaVirtualApplication fva;


    public LiveData<LoginFormState> loginFormState;
    private MutableLiveData<LoginResult> _loginResult;
    public LiveData<LoginResult> loginResult;

    @Inject
    public LoginViewModel(UsuarioRepository usuarioRepository, Gson convertidorJSON, FeriaVirtualApplication fva) {

        this.usuarioRepository = usuarioRepository;
        this.convertidorJSON = convertidorJSON;
        this.fva = fva;

        this._loginForm = new MutableLiveData<LoginFormState>();
        this._loginResult = new MutableLiveData<LoginResult>();
        this.loginResult = _loginResult;
        this.loginFormState = _loginForm;

    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        Call<ResultadoUsuario> result = usuarioRepository.loginUsuario(new LoginObject(username,password));
        SharedPreferences sp = fva.getSharedPreferences(FeriaVirtualConstants.FERIAVIRTUAL_MOVIL_SHARED_PREFERENCES, Context.MODE_PRIVATE);

        try {

            result.enqueue(new Callback<ResultadoUsuario>() {
                @Override
                public void onResponse(Call<ResultadoUsuario> call, Response<ResultadoUsuario> response) {
                    if(response != null && response.isSuccessful() && response.body() != null){

                        ResultadoUsuario rul = response.body();
                        _loginResult.setValue(new LoginResult(new LoggedInUserView( rul.usuario.nombre ),null));
                        if(sp != null){

                            String usuarioString = convertidorJSON.toJson(rul.usuario);

                            sp.edit()
                                .putString(FeriaVirtualConstants.SP_FERIAVIRTUAL_WEBAPI_AUTH_TOKEN,rul.token_usuario)
                                .putString(FeriaVirtualConstants.SP_USUARIO_OBJ_STR,usuarioString)
                                .commit();

                        }

                        Log.i("LOGIN_VIEW_MODEL","Valor de token obtenido!: "+ rul.token_usuario);

                    } else {
                        _loginResult.setValue(new LoginResult(null,R.string.login_failed));
                    }
                }

                @Override
                public void onFailure(Call<ResultadoUsuario> call, Throwable t) {
                    _loginResult.setValue(new LoginResult(null,R.string.login_failed));
                }
            });

            //result.execute();

        } catch( Exception ex ){

            Log.e("LOGIN_VIEW_MODEL","Error en login?: "+ex.toString());
            _loginResult.setValue(new LoginResult(null,R.string.login_failed));

        }

    }

    public void loginDataChanged(String username,String password) {
        if (!isUserNameValid(username)) {
            _loginForm.setValue(new LoginFormState(R.string.invalid_username,null,false));
        } else if (!isPasswordValid(password)) {
            _loginForm.setValue(new LoginFormState(null,R.string.invalid_password,false));
        } else {
            _loginForm.setValue(new LoginFormState(null,null,true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {

        return Patterns.EMAIL_ADDRESS.matcher(username).matches() && !username.isEmpty();

    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password){

        return password.length() >= 5 && !password.isEmpty();

    }
}