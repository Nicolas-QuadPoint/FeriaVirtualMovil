package com.feriantes4dawin.feriavirtualmovil.ui.widgets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.feriantes4dawin.feriavirtualmovil.R;
import com.feriantes4dawin.feriavirtualmovil.ui.auction.PushProductorCustomAdapter;
import com.feriantes4dawin.feriavirtualmovil.ui.util.SimpleAction;

import org.jetbrains.annotations.NotNull;

public final class PushProductorDialog extends SimpleDialog {

    public PushProductorDialog(AppCompatActivity act, SimpleAction positiveResponseFunc, SimpleAction negativeResponseFunc) {
        super(act,positiveResponseFunc,negativeResponseFunc);
    }

    @Override
    protected View prepareView() {

        ViewGroup vg = act.findViewById(android.R.id.content);
        this.v = LayoutInflater.from(act).inflate(R.layout.dialog_push_productor, vg, false);
        RecyclerView rv = (RecyclerView) v.findViewById(R.id.dppb_rvListaProductos);
        PushProductorCustomAdapter rvAdapter = new PushProductorCustomAdapter();

        /**
         * Sacado de https://www.youtube.com/watch?v=M1XEqqo6Ktg
         */
        ItemTouchHelper.SimpleCallback limpiarEnSweep =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView,
                                  @NonNull @NotNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {

                PushProductorCustomAdapter.PushProductorViewHolder vh = (PushProductorCustomAdapter.PushProductorViewHolder)viewHolder;
                vh.limpiarCampos();
                rvAdapter.notifyItemChanged(vh.getAbsoluteAdapterPosition());

            }
        };

        new ItemTouchHelper(limpiarEnSweep).attachToRecyclerView(rv);
        rv.setAdapter(rvAdapter);
        rv.setLayoutManager(new LinearLayoutManager(act));




        return this.v;
    }

    @Override
    protected void prepareResponses() {

        Button btnOk = (Button) v.findViewById(R.id.dppb_btnPujarOfertaProductor);
        Button btnCancelar = (Button) v.findViewById(R.id.dppb_btnCancelar);

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


    }

}
