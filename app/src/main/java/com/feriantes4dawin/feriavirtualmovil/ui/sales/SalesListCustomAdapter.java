package com.feriantes4dawin.feriavirtualmovil.ui.sales;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sale_item_card_layout,parent,false);
        return new SaleListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SaleListViewHolder holder, int position) {

        holder.lblNombreComprador.setText(listaElementos.get(position));

    }

    @Override
    public int getItemCount(){
        return listaElementos.size();
    }

    public class SaleListViewHolder extends RecyclerView.ViewHolder {

        public TextView lblNombreComprador;
        public TextView lblNombreEmpresa;
        public TextView lblHoraVenta;
        public TextView lblEstadoVenta;

        SaleListViewHolder(View v) {

            super(v);

            this.lblNombreComprador = v.findViewById(R.id.sicl_lblNombreNegocio);
            this.lblNombreEmpresa = v.findViewById(R.id.sicl_lblNombreNegocio);
            this.lblHoraVenta = v.findViewById(R.id.sicl_lblHoraVenta);
            this.lblEstadoVenta = v.findViewById(R.id.sicl_lblEstadoVenta);

        }

    }

}