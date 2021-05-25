package com.feriantes4dawin.feriavirtualmovil.ui.widgets;

import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.feriantes4dawin.feriavirtualmovil.R;
import com.feriantes4dawin.feriavirtualmovil.ui.util.SimpleAction;

public abstract class SimpleDialog{

    protected AppCompatActivity act;
    protected SimpleAction positiveResponseFunc;
    protected SimpleAction negativeResponseFunc;
    protected AlertDialog dlg;
    protected View v;

    public SimpleDialog(AppCompatActivity act, SimpleAction positiveResponseFunc, SimpleAction negativeResponseFunc) {
        this.act = act;
        this.positiveResponseFunc = positiveResponseFunc;
        this.negativeResponseFunc = negativeResponseFunc;
    }

    protected abstract View prepareView();
    protected abstract void prepareResponses();

    public AlertDialog generate(){

        AlertDialog.Builder b = new AlertDialog.Builder(act);
        ViewGroup vg = act.findViewById(android.R.id.content);
        this.dlg = b.create();
        dlg.setTitle(R.string.other_stub);
        dlg.setView(prepareView());

        if(this.v != null){

            prepareResponses();
            return this.dlg;

        } else {

            throw new NullPointerException("Can't generate the required View object for AlertDialog");

        }

    }


}
