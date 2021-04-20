package com.feriantes4dawin.feriavirtualmovil.ui.settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.feriantes4dawin.feriavirtualmovil.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

}
