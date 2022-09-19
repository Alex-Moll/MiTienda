package com.miTienda.app.model.mapper;

import com.miTienda.app.model.entity.ProveedorEntity;
import com.miTienda.app.model.request.ProveedorRequest;
import com.miTienda.app.model.response.ProveedorResponse;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProveedorMapper {

    public ProveedorEntity requestToEntity(ProveedorRequest request){

        return ProveedorEntity.builder()
                .razonSocial(request.getRazonSocial().toUpperCase())
                .domicilio(request.getDomicilio().toUpperCase())
                .ciudad(request.getCiudad().toUpperCase())
                .iibb(request.getIibb())
                .telefono(request.getTelefono())
                .telefono1(request.getTelefono1())
                .cuit(request.getCuit())
                .saldo(request.getSaldo())
                .timestamp(request.getTimestamp())
                .build();

    }

    public ProveedorResponse entityToResponse(ProveedorEntity entity){

        return ProveedorResponse.builder()
                .id(entity.getId())
                .razonSocial(entity.getRazonSocial().toUpperCase())
                .domicilio(entity.getDomicilio().toUpperCase())
                .cuit(entity.getCuit())
                .ciudad(entity.getCiudad().toUpperCase())
                .iibb(entity.getIibb())
                .telefono(entity.getTelefono())
                .telefono1(entity.getTelefono1())
                .saldo(entity.getSaldo())
                .timestamp(entity.getTimestamp())
                .build();

    }

    public List<ProveedorResponse> listEntityToListResponse(List<ProveedorEntity> proveedores){
        List<ProveedorResponse> responses = new ArrayList<>();
        for ( ProveedorEntity proveedor: proveedores){
            responses.add(entityToResponse(proveedor));
        }
        return responses;
    }

    public ProveedorEntity entityAndRequestToResponse(ProveedorEntity entity, ProveedorRequest request) {

        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);

        return ProveedorEntity.builder()
                .id(request.getId())
                .razonSocial(request.getRazonSocial().toUpperCase())
                .domicilio(request.getDomicilio().toUpperCase())
                .ciudad(request.getCiudad().toUpperCase())
                .iibb(request.getIibb())
                .telefono(request.getTelefono())
                .telefono1(request.getTelefono1())
                .cuit(request.getCuit())
                .saldo(request.getSaldo())
                .timestamp(request.getTimestamp())
                .build();
//        entity.setRazonSocial(request.getRazonSocial().toUpperCase());
//        entity.setDomicilio(request.getDomicilio().toUpperCase());
//        entity.setCuit(request.getCuit());
//        entity.setCiudad(request.getCiudad().toUpperCase());
//        entity.setIibb(request.getIibb());
//        entity.setTelefono(request.getTelefono());
//        entity.setTelefono1(request.getTelefono1());
//        entity.setSaldo(request.getSaldo());
//        Long datetime = System.currentTimeMillis();
//        Timestamp timestamp = new Timestamp(datetime);
//        entity.setTimestamp(timestamp);
//
//        return entity;

    }

}

