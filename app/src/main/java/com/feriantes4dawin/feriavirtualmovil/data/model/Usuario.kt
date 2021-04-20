package com.feriantes4dawin.feriavirtualmovil.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/*Esto permite tener un solo usuario en la base de datos
* y así asegurar que el que tiene iniciada la sesión sea
* único.
*/
const val CURRENT_LOGGED_USUARIO :Int = 0

@Entity(tableName= "usuario")
data class Usuario(
    @PrimaryKey(autoGenerate = false)
    val secret_id_usuario: Int = CURRENT_LOGGED_USUARIO,
    val id_usuario: Int,
    val personal_id: String,
    val nombre: String,
    val nombre_segundo: String,
    val apellido_materno: String,
    val apellido_paterno: String,
    val direccion: String,
    val telefono: Int,
    val email: String,
    val contrasena:String,
    val estado_contrato: String,
    val estado_usuario: String,
    val nacionalidad: String,
    val rol: String
)