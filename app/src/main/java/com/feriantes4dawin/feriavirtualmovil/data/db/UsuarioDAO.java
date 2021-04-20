package com.feriantes4dawin.feriavirtualmovil.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.feriantes4dawin.feriavirtualmovil.data.model.Usuario;
import com.feriantes4dawin.feriavirtualmovil.ui.util.FeriaVirtualConstants;

/* Anotación que permite definir esta interfaz como una DAO para Room*/
@Dao
public interface UsuarioDAO {

    /* Permite establecer un caso en el cual se da cuando existan datos iguales o distintos*/
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    public void upsert(Usuario u);

    @Query("select * from usuario where secret_id_usuario =0;")
    public LiveData<Usuario> find();

}