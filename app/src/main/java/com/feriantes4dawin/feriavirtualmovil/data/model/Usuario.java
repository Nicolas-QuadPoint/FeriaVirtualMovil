package com.feriantes4dawin.feriavirtualmovil.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.feriantes4dawin.feriavirtualmovil.ui.util.FeriaVirtualConstants;


@Entity(tableName= "usuario")
public class Usuario {
    @PrimaryKey(autoGenerate = false)
    public Integer secret_id_usuario = FeriaVirtualConstants.CURRENT_LOGGED_USUARIO;
    public Integer id_usuario;
    public String personal_id;
    public String nombre;
    public String nombre_segundo;
    public String apellido_materno;
    public String apellido_paterno;
    public String direccion;
    public Integer telefono;
    public String email;
    public String contrasena;
    public String estado_contrato;
    public String estado_usuario;
    public String nacionalidad;
    public String rol;

}