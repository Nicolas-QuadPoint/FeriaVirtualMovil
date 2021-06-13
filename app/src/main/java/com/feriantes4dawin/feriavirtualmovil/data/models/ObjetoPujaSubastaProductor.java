package com.feriantes4dawin.feriavirtualmovil.data.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ObjetoPujaSubastaProductor {
    @PrimaryKey
    public Integer p_secret_pk;
    public Integer p_id_producto;
    public Integer p_kilos;
    public Integer p_precio_kilo;
}
