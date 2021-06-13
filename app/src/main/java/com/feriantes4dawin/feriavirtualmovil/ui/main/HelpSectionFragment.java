package com.feriantes4dawin.feriavirtualmovil.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feriantes4dawin.feriavirtualmovil.R;

/**
 * HelpSectionFragment 
 * 
 * Fragmento cuya única función es mostrar el manual de usuario, 
 * en formato pdf con la ayuda de un webview. 
 */
public class HelpSectionFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_help, container, false);
    }
}