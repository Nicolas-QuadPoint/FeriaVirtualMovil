package com.feriantes4dawin.feriavirtualmovil.ui.widgets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

        return this.v;
    }

    @Override
    protected void prepareResponses() {

        Button btnOk = (Button) v.findViewById(R.id.dpdp_btnPujar);
        Button btnCancelar = (Button) v.findViewById(R.id.dpdp_btnCancelar);

        btnOk.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view){

                if(positiveResponseFunc != null){
                    positiveResponseFunc.doAction(v);
                }

                dlg.cancel();
            }

        });

        //Para respuesta negativa
        btnCancelar.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view){

                if(negativeResponseFunc != null){
                    negativeResponseFunc.doAction(v);
                }

                dlg.cancel();
            }

        });


    }

}
