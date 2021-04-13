package com.feriantes4dawin.feriavirtual.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.feriantes4dawin.feriavirtual.data.model.CURRENT_LOGGED_USUARIO
import com.feriantes4dawin.feriavirtual.data.model.Usuario

/* Anotación que permite definir esta interfaz como una DAO para Room*/
@Dao
interface UsuarioDAO {

    /* Permite establecer un caso en el cual se da cuando existan datos iguales o distintos*/
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun upsert(u: Usuario)

    @Query("select * from usuario where secret_id_usuario = $CURRENT_LOGGED_USUARIO;")
    fun find():LiveData<Usuario>

}