package com.feriantes4dawin.feriavirtualmovil.ui.util;

/**
 * FeriaVirtualConstants 
 * 
 * Clase que contiene constantes utilizadas en toda la 
 * aplicación.
 */
public final class FeriaVirtualConstants {

    /**
     * Identificador de Intent para poder acceder a 
     * la galería de fotos, y escoger una imágen 
     * (Experimental). 
     * @see {@link UtilityFunctions}
     */
    public static final int ESCOGER_IMAGEN_REQUEST = 100;

    /**
     * URL base que indica la dirección del 
     * webapi de FeriaVirtual. 
     */
    public static final String URL_BASE_API_WEB_FERIAVIRTUAL = "http://192.168.100.9:3000/api/v1/";

    /**
     * Identificador del nombre de la base de datos 
     * de la aplicación. 
     */
    public static final String NOMBRE_BASE_DATOS = "feriavirtual.db";

    /**
     * Nombre del encabezado http que será adjuntado a cada 
     * petición a la webapi, con el fin de hacer saber que 
     * tenemos permiso para usar la webapi. El valor que va 
     * junto a este encabezado es el token generado por la 
     * webapi cuando el usuario es autenticado. 
     */
    public static final String SP_FERIAVIRTUAL_WEBAPI_AUTH_TOKEN = "feriavirtual-webapi-auth-token";
    
    /**
     * Identificador para las 'preferencias compartidas' de la 
     * aplicación, con el fin de guardar datos útiles. 
     * @see {@link android.content.SharedPreferences}
     */
    public static final String FERIAVIRTUAL_MOVIL_SHARED_PREFERENCES = "feriavirtual_movil_sp";

    /**
     * Clave de preferencia compartida para guardar el ID del usuario 
     * actualmente autenticado. 
     */
    public static final String SP_USUARIO_ID = "sp_usuario_id";

    /**
     * Clave de preferencia compartida para guardar la cadena con el 
     * objeto JSON del usuario autenticado. 
     */
    public static final String SP_USUARIO_OBJ_STR = "sp_usuario_obj_str";

    /**
     * Clave de preferencia compartida para guardar el ID de la venta 
     * seleccionada en la lista de ventas actuales. 
     */
    public static final String SP_VENTA_ID = "id_venta";

    /**
     * Esto permite tener un solo usuario en la base de datos
     * y así asegurar que el que tiene iniciada la sesión sea
     * único.
     * @see {@link com.feriantes4dawin.feriavirtualmovil.data.models.Usuario}
     */
    public static final int CURRENT_LOGGED_USUARIO = 0;

    /**
     * Identificador para CurrentSalesFragment. 
     * @deprecated Será removida en un futuro dependiendo de su uso actual.
     */
    public static final String FRAGMENTO_LISTA_PETICIONES_VENTA = "FRAGMENTO_LISTA_PETICIONES_VENTA";

    /**
     * Identificador para MyProfileFragment. 
     * @deprecated Será removida en un futuro dependiendo de su uso actual.
     */
    public static final String FRAGMENTO_MI_PERFIL = "FRAGMENTO_MI_PERFIL";

    /**
     * Identificador para MyProcessesFragment. 
     * @deprecated Será removida en un futuro dependiendo de su uso actual.
     */
    public static final String FRAGMENTO_MIS_PROCESOS = "FRAGMENTO_MIS_PROCESOS";

    /**
     * Identificador para HomeFragment. 
     * @deprecated Será removida en un futuro dependiendo de su uso actual.
     */
    public static final String FRAGMENTO_INICIO = "FRAGMENTO_INICIO";
}