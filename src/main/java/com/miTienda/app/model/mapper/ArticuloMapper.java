package com.miTienda.app.model.mapper;

import com.miTienda.app.model.entity.ArticuloEntity;
import com.miTienda.app.model.request.ArticuloRequest;
import com.miTienda.app.model.response.ArticuloResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArticuloMapper {

    @Autowired
    private ProveedorMapper proveedorMapper;

    public ArticuloEntity requestToEntity(ArticuloRequest request){

        Double margen = (request.getPrecioVenta() / request.getPrecioCosto()) * 100;

        margen = (Math.round(margen*100.0)/100.0)-100;

        return ArticuloEntity.builder()
                .codigo(request.getCodigo())
                .categoria(request.getCategoria())
                .detalle(request.getDetalle().toUpperCase())
                .marca(request.getMarca())
                .proveedores(request.getProveedores())
                .proveedor_id(request.getProveedor_id())
                .imagen(request.getImagen())
                .color(request.getColor().toUpperCase())
                .medida(request.getMedida().toUpperCase())
                .stock(request.getStock())
                .stockMin(request.getStockMin())
                .precioCosto(request.getPrecioCosto())
                .margen(margen)
                .precioVenta(request.getPrecioVenta())
                .timestamp(request.getTimestamp())
                .build();

    }

    public ArticuloResponse entityToResponse(ArticuloEntity entity){

        return ArticuloResponse.builder()
                .id(entity.getId())
                .codigo(entity.getCodigo())
                .categoria(entity.getCategoria())
                .detalle(entity.getDetalle())
                .marca(entity.getMarca())
                .proveedor_id(entity.getProveedor_id())
                .proveedores(entity.getProveedores())
                .imagen(entity.getImagen())
                .color(entity.getColor())
                .medida(entity.getMedida())
                .stock(entity.getStock())
                .stockMin(entity.getStockMin())
                .precioCosto(entity.getPrecioCosto())
                .margen(entity.getMargen())
                .precioVenta(entity.getPrecioVenta())
                .timestamp(entity.getTimestamp())
                .build();

    }

    public List<ArticuloResponse> listEntityToListResponse(List<ArticuloEntity> Articuloes){
        List<ArticuloResponse> responses = new ArrayList<>();
        for ( ArticuloEntity articulo: Articuloes){
            responses.add(entityToResponse(articulo));
        }
        return responses;
    }

    public ArticuloEntity entityAndRequestToResponse(ArticuloEntity entity, ArticuloRequest request) {

        Double margen = (request.getPrecioVenta() / request.getPrecioCosto()) * 100;
        margen = (Math.round(margen*100.0)/100.0)-100;

//        entity.setCodigo(request.getCodigo().toUpperCase());
        entity.setDetalle(request.getDetalle().toUpperCase());
        entity.setCategoria(request.getCategoria());
        entity.setMarca(request.getMarca());
        entity.setProveedores(request.getProveedores());
        entity.setProveedor_id(request.getProveedor_id());
        entity.setImagen(request.getImagen());
        entity.setColor(request.getColor().toUpperCase());
        entity.setMedida(request.getMedida().toUpperCase());
        entity.setStock(request.getStock());
        entity.setStockMin(request.getStockMin());
        entity.setPrecioCosto(request.getPrecioCosto());
        entity.setMargen(request.getMargen());
        entity.setPrecioVenta(request.getPrecioVenta());
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        entity.setTimestamp(timestamp);

        return entity;

    }

}