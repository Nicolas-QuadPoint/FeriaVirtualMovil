package com.feriantes4dawin.feriavirtualmovil.data.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ObjetoPujaSubastaTransportista {
    @PrimaryKey
    public Integer p_secret_pk;
    public Subasta subasta;
    public Transportista transportista;
    public String descripcion_propuesta;
    public Integer coste_transporte;
}
