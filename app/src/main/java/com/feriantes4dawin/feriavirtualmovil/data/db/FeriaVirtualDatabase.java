package com.feriantes4dawin.feriavirtualmovil.data.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.feriantes4dawin.feriavirtualmovil.data.models.ClienteExterno;
import com.feriantes4dawin.feriavirtualmovil.data.models.DetallePujaSubastaProductor;
import com.feriantes4dawin.feriavirtualmovil.data.models.EstadoContrato;
import com.feriantes4dawin.feriavirtualmovil.data.models.EstadoSubasta;
import com.feriantes4dawin.feriavirtualmovil.data.models.EstadoUsuario;
import com.feriantes4dawin.feriavirtualmovil.data.models.EstadoVenta;
import com.feriantes4dawin.feriavirtualmovil.data.models.Minorista;
import com.feriantes4dawin.feriavirtualmovil.data.models.Nacionalidad;
import com.feriantes4dawin.feriavirtualmovil.data.models.DetallePujaSubastaTransportista;
import com.feriantes4dawin.feriavirtualmovil.data.models.Producto;
import com.feriantes4dawin.feriavirtualmovil.data.models.Rol;
import com.feriantes4dawin.feriavirtualmovil.data.models.Subasta;
import com.feriantes4dawin.feriavirtualmovil.data.models.TipoProducto;
import com.feriantes4dawin.feriavirtualmovil.data.models.TipoSubasta;
import com.feriantes4dawin.feriavirtualmovil.data.models.TipoVenta;
import com.feriantes4dawin.feriavirtualmovil.data.models.Transportista;
import com.feriantes4dawin.feriavirtualmovil.data.models.Usuario;
import com.feriantes4dawin.feriavirtualmovil.data.models.Venta;
import com.feriantes4dawin.feriavirtualmovil.ui.util.FeriaVirtualConstants;

@Database(
    entities = {
        ClienteExterno.class,
        EstadoVenta.class,
        EstadoSubasta.class,
        EstadoUsuario.class,
        EstadoContrato.class,
        Nacionalidad.class,
        Minorista.class,
        DetallePujaSubastaProductor.class,
        DetallePujaSubastaTransportista.class,
        Producto.class,
        Rol.class,
        Subasta.class,
        TipoProducto.class,
        TipoVenta.class,
        TipoSubasta.class,
        Transportista.class,
        Usuario.class,
        Venta.class
    },
    version = 1,
    exportSchema = false
)
@TypeConverters({
        ObjetoConverter.class
})
public abstract class FeriaVirtualDatabase extends RoomDatabase {

    private static FeriaVirtualDatabase feriaVirtualDatabase = null;
    private final static Object LOCK = new Object();

    public abstract UsuarioDAO getUsuarioDAO();
    public abstract SubastaDAO getSubastaDAO();
    public abstract VentaDAO getVentaDAO();

    /**
     * Crea la instancia de la base de datos.
     * Mantiene adjunto a ??l el contexto general de la aplicaci??n
     * para que cualquiera que desee usar este objeto
     * pueda tener el contexto a su alcance.
     */
    public static FeriaVirtualDatabase getInstance(Context context) {

        synchronized (LOCK) {

            if (feriaVirtualDatabase == null) {

                feriaVirtualDatabase= Room.databaseBuilder(
                        context.getApplicationContext(),
                        FeriaVirtualDatabase.class, FeriaVirtualConstants.NOMBRE_BASE_DATOS)
                        .allowMainThreadQueries()
                    .build();

            }
        }
        return feriaVirtualDatabase;
    }
}