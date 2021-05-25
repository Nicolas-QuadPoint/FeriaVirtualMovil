package com.feriantes4dawin.feriavirtualmovil.ui.widgets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.feriantes4dawin.feriavirtualmovil.R;
import com.feriantes4dawin.feriavirtualmovil.ui.util.SimpleAction;

public final class PushDetailProductorDialog extends SimpleDialog{

    public PushDetailProductorDialog(AppCompatActivity act, SimpleAction positiveResponseFunc, SimpleAction negativeResponseFunc) {
        super(act,positiveResponseFunc,negativeResponseFunc);
    }

    @Override
    protected View prepareView() {
        ViewGroup vg = act.findViewById(android.R.id.content);
        this.v = LayoutInflater.from(act).inflate(R.layout.dialog_push_detail_productor, vg, false);
        dlg.setTitle(R.string.ppbd_title);
        return this.v;
    }

    @Override
    protected void prepareResponses() {

        //Para respuesta positiva
        dlg.setButton(AlertDialog.BUTTON_POSITIVE,act.getString(R.string.ppbd_title),
            (dialogo,idBoton) -> {

                if(positiveResponseFunc != null){
                    positiveResponseFunc.doAction(v);
                }

                dialogo.cancel();
            }
        );

        //Para respuesta negativa
        dlg.setButton(AlertDialog.BUTTON_NEGATIVE,act.getString(R.string.action_cancel),

            (dialogo,idBoton) -> {

                if(negativeResponseFunc != null){
                    negativeResponseFunc.doAction(v);
                }

                dlg.cancel();

            }
        );

    }

}
