package com.feriantes4dawin.feriavirtualmovil.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.feriantes4dawin.feriavirtual.data.model.Usuario
import com.feriantes4dawin.feriavirtual.ui.util.FeriaVirtualConstants.Companion.NOMBRE_BASE_DATOS

@Database(
    entities = [ Usuario::class ],
    version = 1
)
abstract class FeriaVirtualDatabase : RoomDatabase() {

    abstract fun usuarioDAO() : UsuarioDAO

    /**
     * Vamos a hacer de la clase FeriaVirtualDatabase Singleton,
     * osease, que solo se permita una instacia de él en la
     * aplicación
     */
    companion object {

        @Volatile var feriaVirtualDatabase : FeriaVirtualDatabase? = null;

        /* Objeto para permitir un acceso controlado en la base de datos */
        private val LOCK = Any();

        /**
         * Crea la instancia de la base de datos.
         * Mantiene adjunto a él el contexto general de la aplicación
         * para que cualquiera que desee usar este objeto
         * pueda tener el contexto a su alcance.
         */
        fun getInstance(context: Context) : FeriaVirtualDatabase {

            synchronized(LOCK) {
                feriaVirtualDatabase = if(feriaVirtualDatabase == null) {

                    Room.databaseBuilder(context.applicationContext,
                        FeriaVirtualDatabase::class.java, NOMBRE_BASE_DATOS)
                        .build()

                } else {
                    feriaVirtualDatabase
                }
            }
            return feriaVirtualDatabase!!
        }

    }
}