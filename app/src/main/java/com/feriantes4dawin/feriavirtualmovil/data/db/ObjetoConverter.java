package com.feriantes4dawin.feriavirtualmovil.data.db;

import androidx.room.TypeConverter;

import com.feriantes4dawin.feriavirtualmovil.data.models.*;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ObjetoConverter {

    //Conversores genéricos (entrada)

    @TypeConverter
    public String ObjetoAJSON(Object obj){

        return new Gson().toJson(obj);

    }

    @TypeConverter
    public String ListaAJSON(List<?> lista){
        return new Gson().toJson(lista);
    }

    //Conversores para listas de objetos (salida)

    @TypeConverter
    public List<ProductosVenta> JSONAListaProductosVenta(String json){
        return new Gson().fromJson(json, new ArrayList<ProductosVenta>().getClass());
    }

    @TypeConverter
    public List<ObjetoPujaSubastaProductor> JSONAListaObjetosPujaSubastaProductor(String json){
        return new Gson().fromJson(json, new ArrayList<ObjetoPujaSubastaProductor>().getClass());
    }

    @TypeConverter
    public List<ObjetoPujaSubastaTransportista> JSONAListaObjetosPujaSubastaTransportista(String json){
        return new Gson().fromJson(json, new ArrayList<ObjetoPujaSubastaTransportista>().getClass());
    }

    //Conversores para cada tipo (salida)

    @TypeConverter
    public ClienteExterno JSONAClienteExterno(String json){
        return new Gson().fromJson(json, ClienteExterno.class);
    }

    @TypeConverter
    public Transportista JSONATransportista(String json){
        return new Gson().fromJson(json, Transportista.class);
    }

    @TypeConverter
    public EstadoUsuario JSONAEstadoUsuario(String json){
        return new Gson().fromJson(json, EstadoUsuario.class);
    }

    @TypeConverter
    public EstadoContrato JSONAEstadoContrato(String json){
        return new Gson().fromJson(json, EstadoContrato.class);
    }

    @TypeConverter
    public EstadoSubasta JSONAEstadoSubasta(String json){
        return new Gson().fromJson(json, EstadoSubasta.class);
    }

    @TypeConverter
    public EstadoVenta JSONAEstadoVenta(String json){
        return new Gson().fromJson(json, EstadoVenta.class);
    }

    @TypeConverter
    public Minorista JSONAMinorista(String json){
        return new Gson().fromJson(json, Minorista.class);
    }


    @TypeConverter
    public Nacionalidad JSONANacionalidad(String json){
        return new Gson().fromJson(json, Nacionalidad.class);
    }

    @TypeConverter
    public ProductosVenta JSONAProductosVenta(String json){
        return new Gson().fromJson(json, ProductosVenta.class);
    }


    @TypeConverter
    public Rol JSONARol(String json){
        return new Gson().fromJson(json, Rol.class);
    }

    @TypeConverter
    public Subasta JSONASubasta(String json){
        return new Gson().fromJson(json, Subasta.class);
    }

    @TypeConverter
    public TipoVenta JSONATipoVenta(String json){
        return new Gson().fromJson(json, TipoVenta.class);
    }

    @TypeConverter
    public TipoProducto JSONATipoProducto(String json){
        return new Gson().fromJson(json, TipoProducto.class);
    }

    @TypeConverter
    public TipoSubasta JSONATipoSubasta(String json){
        return new Gson().fromJson(json, TipoSubasta.class);
    }

    @TypeConverter
    public Usuario JSONAUsuario(String json){
        return new Gson().fromJson(json, Usuario.class);
    }

    @TypeConverter
    public Venta JSONAVenta(String json){
        return new Gson().fromJson(json, Venta.class);
    }
}
