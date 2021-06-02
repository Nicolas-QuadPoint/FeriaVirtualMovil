package com.feriantes4dawin.feriavirtualmovil.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.feriantes4dawin.feriavirtualmovil.FeriaVirtualApplication;
import com.feriantes4dawin.feriavirtualmovil.FeriaVirtualComponent;
import com.feriantes4dawin.feriavirtualmovil.R;
import com.feriantes4dawin.feriavirtualmovil.data.repos.UsuarioRepositoryImpl;
import com.feriantes4dawin.feriavirtualmovil.ui.main.MainActivity;
import com.feriantes4dawin.feriavirtualmovil.ui.util.FeriaVirtualConstants;
import com.feriantes4dawin.feriavirtualmovil.ui.util.SimpleTextWatcher;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity {

    //private lateinit var loginViewModel: LoginViewModel

    private FeriaVirtualComponent feriaVirtualComponent;

    @Inject
    public UsuarioRepositoryImpl usuarioRepository;

    @Inject
    public Gson convertidorJSON;

    private LoginViewModel loginViewModel;
    private LoginViewModelFactory loginViewModelFactory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        //Injectamos nuestras dependencias
        feriaVirtualComponent =  ((FeriaVirtualApplication) getApplicationContext())
                .getFeriaVirtualComponent();

        feriaVirtualComponent.injectUsuarioRepositoryIntoLoginActivity(this);

        //Creamos nuestro factory!
        loginViewModelFactory = new LoginViewModelFactory(
                usuarioRepository,
                convertidorJSON,
                (FeriaVirtualApplication) getApplicationContext());

        //Creamos el viewmodel con nuestro factory!
        loginViewModel = new ViewModelProvider(this,loginViewModelFactory)
            .get(LoginViewModel.class);

        //Obtengo las preferencias compartidas (por si acaso!)
        SharedPreferences sp = getSharedPreferences(
                FeriaVirtualConstants.FERIAVIRTUAL_MOVIL_SHARED_PREFERENCES,
                Context.MODE_PRIVATE);

        if(revisarSiHaySesion(sp)){

            manejarSesion();

        } else {

            EditText username = findViewById(R.id.username);
            EditText password = findViewById(R.id.password);
            Button login = findViewById(R.id.login);
            LinearLayout loading = findViewById(R.id.dcp_llloading);
            Button  quit = findViewById(R.id.btnQuit);

            TextView linkRegistro = findViewById(R.id.tvLinkRegistro);
            Spanned textolink;

            textolink = HtmlCompat.fromHtml(
                    String.format("%s%s%s",
                            "<a href='https://www.google.com/?hl=es'>",
                            getResources().getString(R.string.launch_user_registry_web),
                            "</a>"),
                    HtmlCompat.FROM_HTML_MODE_COMPACT);

            //Le da el aspecto y acciones de un enlace
            linkRegistro.setText(textolink);
            linkRegistro.setMovementMethod(LinkMovementMethod.getInstance());

            /*
            linkRegistro.setOnClickListener {

                val uri = "https://www.google.com/?hl=es"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(uri)
                startActivity(intent)

            }*/

            loginViewModel.loginFormState.observe(this, new Observer<LoginFormState>() {
                @Override
                public void onChanged(LoginFormState loginFormState) {

                    if(loginFormState == null){
                        return;
                    }

                    // disable login button unless both username / password is valid
                    login.setEnabled(loginFormState.isDataValid);

                    if (loginFormState.usernameError != null) {
                        username.setError(getString(R.string.invalid_username));
                    } else {
                        username.setError(null);
                    }
                    if (loginFormState.passwordError != null) {
                        password.setError(getString(R.string.invalid_password));
                    } else {
                        password.setError(null);
                    }
                }
            });

            loginViewModel.loginResult.observe(this, new Observer<LoginResult>() {
                @Override
                public void onChanged(LoginResult loginResult) {

                    if(loginResult == null){
                        return;
                    }

                    loading.setVisibility(View.GONE);

                    if (loginResult.error != null) {
                        showLoginFailed(loginResult.error);
                    }
                    if (loginResult.success != null) {

                        updateUiWithUser(loginResult.success);
                        manejarSesion();

                    }

                }
            });

            username.addTextChangedListener(
                    new SimpleTextWatcher(null) {
                        @Override
                        public void afterTextChanged(Editable s) {
                            loginViewModel.loginDataChanged(
                                    username.getText().toString(),
                                    password.getText().toString()
                            );
                        }
                    }
            );

            password.addTextChangedListener(
                    new SimpleTextWatcher(null) {
                        @Override
                        public void afterTextChanged(Editable s) {
                            loginViewModel.loginDataChanged(
                                    username.getText().toString(),
                                    password.getText().toString()
                            );
                        }
                    }
            );

            password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if(actionId == EditorInfo.IME_ACTION_DONE){
                        loginViewModel.login(
                                username.getText().toString(),
                                password.getText().toString()
                        );
                    }
                    return false;
                }
            });

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loading.setVisibility(View.VISIBLE);
                    loginViewModel.login(username.getText().toString(), password.getText().toString());
                }
            });

            quit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        }

    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome);
        String displayName = model.displayName;
        // TODO : initiate successful logged in experience
        Toast.makeText(
            getApplicationContext(),
            String.format("%s, %s",welcome,model.displayName),
            Toast.LENGTH_LONG
        ).show();
    }

    private void showLoginFailed(int errorString) {
        View v = findViewById(R.id.actlog_svPrincipal);
        Snackbar.make(v, getString(errorString), Snackbar.LENGTH_SHORT).show();
    }


    public boolean revisarSiHaySesion(SharedPreferences sp){

        return (!sp.getString(FeriaVirtualConstants.SP_FERIAVIRTUAL_WEBAPI_AUTH_TOKEN,"").equals(""))
                && (!sp.getString(FeriaVirtualConstants.SP_USUARIO_OBJ_STR,"").equals(""));

    }


    public void manejarSesion(){

        //Cambiamos a la actividad principal
        Intent menuprincipalintent = new Intent(getApplicationContext(), MainActivity.class);

        startActivity(menuprincipalintent);

        setResult(Activity.RESULT_OK);

        //Complete and destroy login activity once successful
        finish();

    }
}