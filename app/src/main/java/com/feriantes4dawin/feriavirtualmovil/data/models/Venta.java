package com.feriantes4dawin.feriavirtualmovil.data.models;

import java.util.List;

public class Venta{
    public Integer id_venta;
    public String fecha_inicio_venta;
    public String fecha_fin_venta;
    public String comentarios_venta;
    public Integer monto_total;
    public Integer comision;
    public Usuario usuario_autor;
    public EstadoVenta estado_venta;
    public TipoVenta tipo_venta;
    public List<ProductosVenta> productos_venta;
}
