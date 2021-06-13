package com.feriantes4dawin.feriavirtualmovil.ui.widgets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.feriantes4dawin.feriavirtualmovil.R;
import com.feriantes4dawin.feriavirtualmovil.ui.util.SimpleAction;

import org.jetbrains.annotations.NotNull;

/**
 * PushTransportistaDialog 
 * 
 * Una implementación de SimpleDialog que contiene la lógica 
 * necesaria para mostrar un diálogo de puja de subasta, para 
 * usuarios transportistas, que dista de la puja para usuarios 
 * productores, siendo este primero más simple. 
 */
public class PushTransportistaDialog extends SimpleDialog {

    public PushTransportistaDialog(AppCompatActivity act, SimpleAction positiveResponseFunc, SimpleAction negativeResponseFunc) {
        super(act, positiveResponseFunc, negativeResponseFunc);
    }

    @Override
    protected View prepareView() {

        ViewGroup vg = act.findViewById(android.R.id.content);
        this.v = LayoutInflater.from(act).inflate(R.layout.dialog_push_transportista, vg, false);
        dlg.setTitle(R.string.ppbd_push);

        return this.v;

    }

    @Override
    protected void prepareResponses() {


    }
}
