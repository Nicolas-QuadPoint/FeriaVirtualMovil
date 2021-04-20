package com.feriantes4dawin.feriavirtualmovil.data.repos

import androidx.lifecycle.LiveData
import com.feriantes4dawin.feriavirtual.data.db.UsuarioDAO
import com.feriantes4dawin.feriavirtual.data.model.Usuario
import com.feriantes4dawin.feriavirtual.data.network.UsuarioDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsuarioRepositoryImpl (
        private var usuarioDAO: UsuarioDAO,
        private var usuarioDataSource : UsuarioDataSource) : UsuarioRepository {

    init {

        usuarioDataSource.apply {

            usuarioDescargado.observeForever{ nuevoUsuarioResponse ->

                saveUsuarioToDatabase(nuevoUsuarioResponse.usuario)

            }

        }

    }


    override suspend fun loginUsuario(email: String, contrasena: String): Boolean {

        return withContext(Dispatchers.IO){

            val u = usuarioDAO.find()
            return@withContext ((u.value != null) and (u.value!!.email == email) and (u.value!!.contrasena == contrasena))

        }

    }

    override suspend fun getInfoUsuario(u: Usuario): LiveData<Usuario> {

        return withContext(Dispatchers.IO){

            return@withContext usuarioDAO.find()

        }

    }

    private fun saveUsuarioToDatabase(usuario: Usuario){

        GlobalScope.launch(Dispatchers.IO){

            //Insertamos el usuario recuperado de login o getInfoUsuario
            //a la base de datos!
            usuarioDAO.upsert(usuario)

        }

    }
}