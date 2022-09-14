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
                .nombre(request.getNombre())
                .domicilio(request.getDomicilio())
                .cuit(request.getCuit())
                .saldo(request.getSaldo())
                .timestamp(request.getTimestamp())
                .build();

    }

    public ProveedorResponse entityToResponse(ProveedorEntity entity){

        return ProveedorResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .domicilio(entity.getDomicilio())
                .cuit(entity.getCuit())
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

        entity.setNombre(request.getNombre());
        entity.setDomicilio(request.getDomicilio());
        entity.setCuit(request.getCuit());
        entity.setSaldo(request.getSaldo());
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        entity.setTimestamp(timestamp);

        return entity;

    }

}

