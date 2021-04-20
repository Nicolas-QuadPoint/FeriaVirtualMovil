package com.feriantes4dawin.feriavirtualmovil.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.feriantes4dawin.feriavirtual.R
import com.feriantes4dawin.feriavirtual.ui.login.LoginActivity
import com.feriantes4dawin.feriavirtualmovil.ui.settings.SettingsActivity
import com.feriantes4dawin.feriavirtual.ui.util.FeriaVirtualConstants
import com.feriantes4dawin.feriavirtualmovil.ui.widgets.YesNoDialog
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(){

    //override val di by closestDI()

    private lateinit var appBarConfiguration: AppBarConfiguration

    lateinit private var mainViewmodel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        mainViewmodel = ViewModelProvider(this).get(MainViewModel::class.java)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_sales, R.id.nav_processes, R.id.nav_profile
        ), drawerLayout)

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //Using only nav_logout for quit program
        //val navLogout = findViewById<MenuItem>(R.id.nav_logout)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)

        return true
    }

    /**
     * Este metodo resuelve la accion seleccionada del menu superior de la actividad
     */
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {

        R.id.action_settings -> {
            // User chose the "Settings" item, show the app settings UI...

            val settingsActivityIntent = Intent(this,
                _root_ide_package_.com.feriantes4dawin.feriavirtualmovil.ui.settings.SettingsActivity::class.java)

            startActivity(settingsActivityIntent)

            true
        }

        R.id.action_logout -> {

            val yesno =
                _root_ide_package_.com.feriantes4dawin.feriavirtualmovil.ui.widgets.YesNoDialog(this,
                    getString(R.string.err_mes_question),
                    getString(R.string.err_msg_logout_prompt),
                    {
                        val loginActivityIntent = Intent(this, LoginActivity::class.java)
                        //Con esto nos evitamos la molestia de de que el usuario regrese al menu principal
                        //loo presionando la tecla atrás
                        loginActivityIntent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        //TODO: Give special parameters to activity for clean login cache or so...
                        startActivity(loginActivityIntent)
                        //Ultima esperanza!
                        //finish()

                    },
                    null
                )

            yesno.show()

            true

        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val ubicacionImagenSeleccionada : Uri?


        if (resultCode == RESULT_OK && requestCode == FeriaVirtualConstants.ESCOGER_IMAGEN_REQUEST){

            ubicacionImagenSeleccionada = data?.getData()

            Log.i("[MAIN_ACTIVITY]","Imágen recuperada: " + ( if (ubicacionImagenSeleccionada == null) { "null" } else { ubicacionImagenSeleccionada.path }) )
        }

    }

    fun navLogoutClick(v : View){

        Log.i("[MAIN_ACTIVITY]","Logout request!")

    }
}