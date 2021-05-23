package com.feriantes4dawin.feriavirtualmovil.ui.settings;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.feriantes4dawin.feriavirtualmovil.R;
import com.feriantes4dawin.feriavirtualmovil.ui.util.FeriaVirtualConstants;

public class SettingsActivity extends AppCompatActivity {

    private SettingsViewModel viewmodel;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        if (savedInstanceState == null) {
            this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings2, new SettingsFragment())
                .commit();
        }
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        this.viewmodel = new ViewModelProvider(this).get(SettingsViewModel.class);
    }

    @Override
    public void onBackPressed() {
        // your code.
        finish();
    }

}