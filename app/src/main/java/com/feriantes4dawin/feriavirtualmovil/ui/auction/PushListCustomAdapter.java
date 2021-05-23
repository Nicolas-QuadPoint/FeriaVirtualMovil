package com.feriantes4dawin.feriavirtualmovil.ui.auction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.feriantes4dawin.feriavirtualmovil.R;

import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class PushListCustomAdapter extends RecyclerView.Adapter<PushListCustomAdapter.PushListViewHolder> {

    private ArrayList<String> listaElementos;

    public PushListCustomAdapter(){

        this.listaElementos = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            listaElementos.add(String.format( "%s %d","usuario",i  ));
        }


    }

    @NonNull
    @NotNull
    @Override
    public PushListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_push_info_item,parent,false);
        return new PushListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PushListViewHolder holder, int position) {

        holder.lblCosteTotalPuja.setText( Integer.toString( Math.round( (float)Math.random() * 100) ) );
        holder.lblFechaPuja.setText( new Date().toString() );
        holder.lblNombreUsuario.setText( listaElementos.get(position) );
        holder.id_puja = Integer.toString( Math.round( ( (float)Math.random() * 100 ) + 1 ) );

    }

    @Override
    public int getItemCount() {
        return listaElementos.size();
    }

    public void pushItem(String n){
        listaElementos.add(n);
        notifyItemInserted(listaElementos.size());
    }

    public class PushListViewHolder extends RecyclerView.ViewHolder{

        public String id_puja;
        public TextView lblNombreUsuario;
        public TextView lblFechaPuja;
        public TextView lblCosteTotalPuja;

        public PushListViewHolder(View v){

            super(v);

            this.lblNombreUsuario = v.findViewById(R.id.pii_lblNombreUsuario);
            this.lblFechaPuja = v.findViewById(R.id.pii_lblFechaPuja);
            this.lblCosteTotalPuja = v.findViewById(R.id.pii_lblCostoTotalPuja);

        }
    }
}
