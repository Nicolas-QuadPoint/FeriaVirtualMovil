package com.feriantes4dawin.feriavirtualmovil.data.models;

import java.util.List;

public class Subasta {
    public Integer id_subasta;
    public String fecha_inicio_subasta;
    public String fecha_fin_subasta;
    public TipoSubasta tipo_subasta;
    public EstadoSubasta estado_subasta;
    public Venta venta;
    public List<Object> ofertas_productores;
    public List<Object> ofertas_transportistas;
}
