package com.feriantes4dawin.feriavirtual.ui.sales

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.feriantes4dawin.feriavirtual.R

class SalesListCustomAdapter : RecyclerView.Adapter<SalesListCustomAdapter.SaleListViewHolder>() {

    private var listaElementos:MutableList<String>

    init{

        listaElementos = mutableListOf<String>()

        listaElementos.add("Empresa 1")
        listaElementos.add("Empresa 2")
        listaElementos.add("Empresa 3")
        listaElementos.add("Empresa 4")
        listaElementos.add("Empresa 5")

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleListViewHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sale_item_card_layout,parent,false)
        return  SaleListViewHolder(view)
    }

    override fun onBindViewHolder(holder: SaleListViewHolder, position: Int) {

        holder.lblNombreComprador.text = listaElementos[position]

    }

    override fun getItemCount(): Int {
        return listaElementos.size
    }

    class SaleListViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var lblNombreComprador : TextView
        var lblNombreEmpresa : TextView
        var lblHoraVenta : TextView
        var lblEstadoVenta : TextView

        init {

            lblNombreComprador = v.findViewById(R.id.sicl_lblNombreNegocio)
            lblNombreEmpresa = v.findViewById(R.id.sicl_lblNombreNegocio)
            lblHoraVenta = v.findViewById(R.id.sicl_lblHoraVenta)
            lblEstadoVenta = v.findViewById(R.id.sicl_lblEstadoVenta)

        }

    }

}