package com.feriantes4dawin.feriavirtualmovil.ui.widgets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.feriantes4dawin.feriavirtualmovil.R;
import com.feriantes4dawin.feriavirtualmovil.ui.util.SimpleAction;

import java.util.ArrayList;

public class PushProductorBidDialog {

    private AppCompatActivity act;
    private SimpleAction positiveResponseFunc;
    private SimpleAction negativeResponseFunc;
    private View v;

    public PushProductorBidDialog(AppCompatActivity act, SimpleAction positiveResponseFunc, SimpleAction negativeResponseFunc) {
        this.act = act;
        this.positiveResponseFunc = positiveResponseFunc;
        this.negativeResponseFunc = negativeResponseFunc;
    }

    public AlertDialog generate(){

        AlertDialog.Builder b = new AlertDialog.Builder(act);
        ViewGroup vg = act.findViewById(android.R.id.content);
        this.v = LayoutInflater.from(act).inflate(R.layout.dialog_push_productor_bid, vg, false);

        if(v != null){

            //Insertamos la vista al diálogo
            b.setView(v);
            ArrayList<String> listaOpciones = new ArrayList<String>();
            AlertDialog dlg = b.create();
            Button btnOk = (Button) v.findViewById(R.id.dppb_btnPujarOfertaProductor);
            Button btnCancelar = (Button) v.findViewById(R.id.dppb_btnCancelar);
            RecyclerView rv = (RecyclerView) v.findViewById(R.id.dppb_rvListaProductos);
            Spinner sp = (Spinner) v.findViewById(R.id.dppb_spListaProductos);

            //No estableceremos aun la informacion para el adaptador de lista de productos
            rv.setAdapter(null);
            listaOpciones.add("Nada");
            ArrayAdapter<String> listaAdapter = new ArrayAdapter<String>(act,android.R.layout.simple_spinner_item,listaOpciones);
            listaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp.setAdapter(listaAdapter);


            //Para respuesta alternativa
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

            //passwdFormLiveState.observe(act,)

            return dlg;

        } else {

            throw new NullPointerException("Can't generate the required View object for AlertDialog");

        }

    }

}
