package com.feriantes4dawin.feriavirtualmovil.data.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

import java.util.Objects;

@Entity
public class Rol{

    @Expose
    @PrimaryKey
    public Integer id_rol;

    @Expose
    public String descripcion;






    /**
     * Valor de rol para Administrador.
     */
    public static final Rol ADMINISTRADOR = new Rol(1,"Administrador");

    /**
     * @deprecated
     * Valor de rol para Estadista (No usado, y posiblemente sea removida
     * en alguna actualización!)
     */
    public static final Rol ESTADISTA = new Rol(2,"Estadista");

    /**
     * Valor de rol para Minorista
     */
    public static final Rol MINORISTA = new Rol(5,"Minorista");

    /**
     * Valor de rol para Transportista
     */
    public static final Rol TRANSPORTISTA = new Rol(6,"Transportista");

    /**
     * Valor de rol para Productor
     */
    public static final Rol PRODUCTOR = new Rol(7,"Productor");

    /**
     * Valor de rol para Cliente externo
     */
    public static final Rol CLIENTE_EXTERNO = new Rol(4,"Cliente Externo");

    public Rol(){
        this.id_rol = 0;
        this.descripcion = "ninguno";
    }

    public Rol(Integer id_rol,String descripcion){
        this.id_rol = id_rol;
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rol rol = (Rol) o;
        return Objects.equals(id_rol, rol.id_rol) &&
                Objects.equals(descripcion, rol.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_rol, descripcion);
    }
}