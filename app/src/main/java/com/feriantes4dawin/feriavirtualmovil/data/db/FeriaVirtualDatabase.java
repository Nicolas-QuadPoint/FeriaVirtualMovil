package com.feriantes4dawin.feriavirtualmovil.data.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.feriantes4dawin.feriavirtualmovil.data.model.Usuario;
import com.feriantes4dawin.feriavirtualmovil.ui.util.FeriaVirtualConstants;

@Database(
    entities = { Usuario.class },
    version = 1
)
public abstract class FeriaVirtualDatabase extends RoomDatabase {

    public abstract UsuarioDAO usuarioDAO();
    private static FeriaVirtualDatabase feriaVirtualDatabase = null;
    private static Object LOCK = new Object();


    /**
     * Crea la instancia de la base de datos.
     * Mantiene adjunto a él el contexto general de la aplicación
     * para que cualquiera que desee usar este objeto
     * pueda tener el contexto a su alcance.
     */
    public static FeriaVirtualDatabase getInstance(Context context) {

        synchronized (LOCK) {

            if (feriaVirtualDatabase == null) {

                feriaVirtualDatabase= Room.databaseBuilder(
                        context.getApplicationContext(),
                        FeriaVirtualDatabase.class, FeriaVirtualConstants.NOMBRE_BASE_DATOS)
                    .build();

            }
        }
        return feriaVirtualDatabase;
    }
}