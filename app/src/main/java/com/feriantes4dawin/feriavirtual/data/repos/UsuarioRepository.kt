package com.feriantes4dawin.feriavirtual.data.repos

import androidx.lifecycle.LiveData
import com.feriantes4dawin.feriavirtual.data.db.UsuarioDAO
import com.feriantes4dawin.feriavirtual.data.model.Usuario
import com.feriantes4dawin.feriavirtual.data.network.responses.UsuarioResponse

interface UsuarioRepository {
    suspend fun loginUsuario(email:String,contrasena:String): Boolean
    suspend fun getInfoUsuario(u: Usuario) : LiveData<Usuario>
}