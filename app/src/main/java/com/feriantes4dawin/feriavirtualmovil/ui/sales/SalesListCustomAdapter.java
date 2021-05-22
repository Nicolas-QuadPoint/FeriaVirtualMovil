package com.feriantes4dawin.feriavirtualmovil.ui.sales;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.feriantes4dawin.feriavirtualmovil.R;

import java.util.ArrayList;

public class SalesListCustomAdapter extends RecyclerView.Adapter<SalesListCustomAdapter.SaleListViewHolder> {

    private ArrayList<String> listaElementos;

    public SalesListCustomAdapter(){

        super();

        this.listaElementos = new ArrayList<String>();
        this.listaElementos.add("Empresa 1");
        this.listaElementos.add("Empresa 2");
        this.listaElementos.add("Empresa 3");
        this.listaElementos.add("Empresa 4");
        this.listaElementos.add("Empresa 5");

    }

    @Override
    public SaleListViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_sale_item,parent,false);
        SaleListViewHolder vh = new SaleListViewHolder(view);

        view.setOnClickListener(v -> {
            Intent i = new Intent(parent.getContext(),SaleDetailActivity.class);
            i.putExtra("id_venta",vh.id_venta);
            parent.getContext().startActivity(i);
        });



        return new SaleListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SaleListViewHolder holder, int position) {

        holder.lblNombreEmpresa.setText(listaElementos.get(position));

    }

    @Override
    public int getItemCount(){
        return listaElementos.size();
    }

    public class SaleListViewHolder extends RecyclerView.ViewHolder {

        public String id_venta;
        public TextView lblNombreEmpresa;
        public TextView lblHoraVenta;
        public TextView lblEstadoVenta;

        SaleListViewHolder(View v) {

            super(v);

            this.lblNombreEmpresa = v.findViewById(R.id.csi_lblNombreNegocio);
            this.lblHoraVenta = v.findViewById(R.id.csi_lblFechaVenta);
            this.lblEstadoVenta = v.findViewById(R.id.csi_lblEstadoVenta);

        }

    }

}