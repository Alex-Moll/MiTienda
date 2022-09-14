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

        margen = Math.round(margen*100.0)/100.0;

        return ArticuloEntity.builder()
                .codigo(request.getCodigo())
                .tipoArticulo(request.getTipoArticulo())
                .detalle(request.getDetalle())
                .marca(request.getMarca())
                .proveedores(request.getProveedores())
                .proveedor_id(request.getProveedor_id())
                .color(request.getColor())
                .medida(request.getMedida())
                .stock(request.getStock())
                .stockMin(request.getStockMin())
                .precioCosto(request.getPrecioCosto())
                .margen(margen)
                .precioVenta(request.getPrecioVenta())
                .timestamp(request.getTimestamp())
                .build();

    }

//    public ArticuloResponse entityToResponse(ArticuloEntity entity, boolean loadProveedores){
//
//        return ArticuloResponse.builder()
//                .id(entity.getId())
//                .codigo(entity.getCodigo())
//                .tipoArticulo(entity.getTipoArticulo())
//                .detalle(entity.getDetalle())
//                .marca(entity.getMarca())
//                .proveedor_id(entity.getProveedor_id())
//                .proveedores(entity.getProveedores())
//                .color(entity.getColor())
//                .stock(entity.getStock())
//                .precioCosto(entity.getPrecioCosto())
//                .precioVenta(entity.getPrecioVenta())
//                .timestamp(entity.getTimestamp())
//                .build();
//
//    }

    public ArticuloResponse entityToResponse(ArticuloEntity entity, boolean loadProveedores){

        ArticuloResponse response = new ArticuloResponse();

        response.setCodigo(entity.getCodigo());
        response.setTipoArticulo(entity.getTipoArticulo());
        response.setDetalle(entity.getDetalle());
        response.setMarca(entity.getMarca());
        response.setProveedor_id(entity.getProveedor_id());
        response.setColor(entity.getColor());
        response.setMedida(entity.getMedida());
        response.setStock(entity.getStock());
        response.setStockMin(entity.getStockMin());
        response.setPrecioCosto(entity.getPrecioCosto());
        response.setMargen(entity.getMargen());
        response.setPrecioVenta(entity.getPrecioVenta());
        response.setTimestamp(entity.getTimestamp());
        response.setProveedores(entity.getProveedores());

        return response;
    }

    public List<ArticuloResponse> listEntityToListResponse(List<ArticuloEntity> Articuloes){
        List<ArticuloResponse> responses = new ArrayList<>();
        for ( ArticuloEntity articulo: Articuloes){
            responses.add(entityToResponse(articulo, true));
        }
        return responses;
    }

    public ArticuloEntity entityAndRequestToResponse(ArticuloEntity entity, ArticuloRequest request) {

        entity.setDetalle(request.getDetalle());
        entity.setTipoArticulo(request.getTipoArticulo());
        entity.setMarca(request.getMarca());
        entity.setProveedores(request.getProveedores());
        entity.setProveedor_id(request.getProveedor_id());
        entity.setColor(request.getColor());
        entity.setMedida(request.getMedida());
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