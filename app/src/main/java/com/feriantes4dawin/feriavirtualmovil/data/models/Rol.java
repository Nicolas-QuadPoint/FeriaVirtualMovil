package com.feriantes4dawin.feriavirtualmovil.data.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Rol{
    @PrimaryKey
    public Integer id_rol;
    public String descripcion;
}