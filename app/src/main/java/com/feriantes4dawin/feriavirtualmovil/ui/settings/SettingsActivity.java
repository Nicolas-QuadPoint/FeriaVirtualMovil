package com.feriantes4dawin.feriavirtualmovil.ui.settings;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceFragmentCompat;
import com.feriantes4dawin.feriavirtual.R;

public class SettingsActivity extends AppCompatActivity {

    private SettingsViewModel viewmodel;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

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

    public class SettingsFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState,String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }

    }
}