package com.feriantes4dawin.feriavirtualmovil.ui.widgets;

import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.feriantes4dawin.feriavirtualmovil.R;
import com.feriantes4dawin.feriavirtualmovil.ui.util.SimpleAction;
import com.feriantes4dawin.feriavirtualmovil.ui.util.SimpleTextWatcher;

import dagger.Component;

public final class PushDetailProductorDialog extends SimpleDialog{

    private String productName;

    public PushDetailProductorDialog(AppCompatActivity act,String productName, SimpleAction positiveResponseFunc, SimpleAction negativeResponseFunc) {
        super(act,positiveResponseFunc,negativeResponseFunc);
        this.productName = productName;
    }

    @Override
    protected View prepareView() {

        ViewGroup vg = act.findViewById(android.R.id.content);
        this.v = LayoutInflater.from(act).inflate(R.layout.dialog_push_detail_productor, vg, false);

        EditText txtCantidad = v.findViewById(R.id.liiqp_txtUnidades);
        EditText txtCoste = v.findViewById(R.id.liiqp_txtCostePorUnidad);
        ComparadorTextWatcher comparador = new ComparadorTextWatcher(txtCantidad,txtCoste);

        txtCantidad.addTextChangedListener(comparador);
        txtCoste.addTextChangedListener(comparador);

        dlg.setTitle(
            String.format("%s: %s",
                act.getString(R.string.ppbd_title),
                productName != null?
                        productName : act.getString(R.string.err_mes_not_avalaible)
            )
        );



        return this.v;
    }

    @Override
    public AlertDialog generate() {

        AlertDialog ad = super.generate();

        ad.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {

                ad.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);

            }
        });

        return ad;
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

    public class ComparadorTextWatcher extends SimpleTextWatcher {

        private EditText txt2;

        public ComparadorTextWatcher(EditText txt, EditText txt2){
            super(txt);
            this.txt2 = txt2;
        }

        @Override
        public void afterTextChanged(Editable e) {
            if ( (e.toString().isEmpty() || e.toString().equals("0")) ||
                    ( (txt2.getText().toString().isEmpty() || txt2.getText().toString().equals("0")) )) {

                txt.setError("Completa los campos faltantes");
                dlg.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

            } else {

                txt.setError(null);
                dlg.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);

            }
        }

    }

}
