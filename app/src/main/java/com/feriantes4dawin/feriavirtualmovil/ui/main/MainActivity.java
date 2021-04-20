package com.feriantes4dawin.feriavirtualmovil.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.feriantes4dawin.feriavirtualmovil.R;
import com.feriantes4dawin.feriavirtualmovil.ui.login.LoginActivity;
import com.feriantes4dawin.feriavirtualmovil.ui.settings.SettingsActivity;
import com.feriantes4dawin.feriavirtualmovil.ui.util.FeriaVirtualConstants;
import com.feriantes4dawin.feriavirtualmovil.ui.util.SimpleAction;
import com.feriantes4dawin.feriavirtualmovil.ui.widgets.YesNoDialog;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity{

    //override val di by closestDI()

    private AppBarConfiguration appBarConfiguration;

    private MainViewModel mainViewmodel;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.mainViewmodel = new ViewModelProvider(this).get(MainViewModel.class);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_sales, R.id.nav_processes, R.id.nav_profile)
                .setOpenableLayout(drawerLayout).build();

        NavigationUI.setupActionBarWithNavController(this, navController,appBarConfiguration);
        NavigationUI.setupWithNavController(navView,navController);

        //Using only nav_logout for quit program
        //val navLogout = findViewById<MenuItem>(R.id.nav_logout)
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Este metodo resuelve la accion seleccionada del menu superior de la actividad
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){
            case R.id.action_settings:{
                // User chose the "Settings" item, show the app settings UI...

                Intent settingsActivityIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsActivityIntent);

                return true;
            }
            case R.id.action_logout:{

                YesNoDialog yesno = new YesNoDialog(this, getString(R.string.err_mes_question), getString(R.string.err_msg_logout_prompt),
                        new SimpleAction() {
                            @Override
                            public void doAction(Object o) {
                                Intent loginActivityIntent = new Intent(getApplicationContext(), LoginActivity.class);
                                //Con esto nos evitamos la molestia de de que el usuario regrese al menu principal
                                //loo presionando la tecla atrás
                                loginActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                //TODO: Give special parameters to activity for clean login cache or so...
                                startActivity(loginActivityIntent);
                                //Ultima esperanza!
                                //finish()
                            }
                        },
                    null
                );

                yesno.show();

                return true;

            }
            default:{
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
            }
        }


    }


    @Override
    public boolean onSupportNavigateUp() {

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();

    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri ubicacionImagenSeleccionada;

        if (resultCode == RESULT_OK && requestCode == FeriaVirtualConstants.ESCOGER_IMAGEN_REQUEST){

            ubicacionImagenSeleccionada = data.getData();

            Log.i("[MAIN_ACTIVITY]",String.format("Imágen recuperada: %s",
                    (ubicacionImagenSeleccionada == null)? "null" : ubicacionImagenSeleccionada.getPath()));
        }

    }

    public void navLogoutClick(View v){

        Log.i("[MAIN_ACTIVITY]","Logout request!");

    }
}