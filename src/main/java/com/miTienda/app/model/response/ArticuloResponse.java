package com.miTienda.app.model.response;

import com.miTienda.app.model.entity.MarcaEntity;
import com.miTienda.app.model.entity.ProveedorEntity;
import com.miTienda.app.model.entity.CategoriaEntity;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticuloResponse {

    private Long id;
    private String codigo;
    private CategoriaEntity categoria;
    private String detalle;
    private MarcaEntity marca;
    private List<ProveedorEntity> proveedores = new ArrayList<>();
    private String proveedor_id;
    private String imagen;
    private String color;
    private String medida;
    private double stock;
    private double stockMin;
    private double precioCosto;
    private double margen;
    private double precioVenta;
    private Timestamp timestamp;
    private boolean softDelete = false;

}
