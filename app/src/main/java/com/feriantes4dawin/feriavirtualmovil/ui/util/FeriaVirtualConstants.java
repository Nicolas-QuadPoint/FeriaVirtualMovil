package com.feriantes4dawin.feriavirtualmovil.ui.util;

public class FeriaVirtualConstants {

    public static final int ESCOGER_IMAGEN_REQUEST = 100;
    public static final String URL_BASE_API_WEB_FERIAVIRTUAL = "http://192.168.100.7:3000/api/v1/";
    public static final String NOMBRE_BASE_DATOS = "feriavirtual.db";

    public static final String SP_FERIAVIRTUAL_WEBAPI_AUTH_TOKEN = "feriavirtual-webapi-auth-token";
    public static final String FERIAVIRTUAL_MOVIL_SHARED_PREFERENCES = "feriavirtual_movil_sp";

    public static final String SP_USUARIO_ID = "sp_usuario_id";
    public static final String SP_USUARIO_OBJ_STR = "sp_usuario_obj_str";
    public static final String SP_VENTA_ID = "id_venta";

    /*Esto permite tener un solo usuario en la base de datos
     * y así asegurar que el que tiene iniciada la sesión sea
     * único.
     */
    public static final int CURRENT_LOGGED_USUARIO = 0;

    /**
     * Strings que representan el orden de cada fragmento, para que, cuando
     * se quiera retroceder en una actividad hija, entonces en el MainActivity
     * se navegue inmediatamente al fragmento 'padre'
     */
    public static final String FRAGMENTO_LISTA_PETICIONES_VENTA = "FRAGMENTO_LISTA_PETICIONES_VENTA";
    public static final String FRAGMENTO_MI_PERFIL = "FRAGMENTO_MI_PERFIL";
    public static final String FRAGMENTO_MIS_PROCESOS = "FRAGMENTO_MIS_PROCESOS";
    public static final String FRAGMENTO_INICIO = "FRAGMENTO_INICIO";
}