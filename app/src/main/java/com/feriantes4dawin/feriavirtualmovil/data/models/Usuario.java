package com.feriantes4dawin.feriavirtualmovil.data.models;

import androidx.room.PrimaryKey;

import com.feriantes4dawin.feriavirtualmovil.ui.util.FeriaVirtualConstants;

public class Usuario {

    @PrimaryKey(autoGenerate = false)
    public Integer secret_id_usuario = FeriaVirtualConstants.CURRENT_LOGGED_USUARIO;
    public Integer id_usuario;
    public String personal_id;
    public String nombre;
    public String nombre_segundo;
    public String apellido_paterno;
    public String apellido_materno;
    public String fecha_nacimiento;
    public Integer telefono;
    public String direccion;
    public String email;
    public String contrasena;
    public String salt_contrasena;
    public Nacionalidad nacionalidad;
    public Rol rol;
    public EstadoUsuario estado_usuario;
    public EstadoContrato estado_contrato;
    
}
