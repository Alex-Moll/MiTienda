package com.miTienda.app.model.mapper;


import com.miTienda.app.model.entity.ClienteEntity;
import com.miTienda.app.model.request.ClienteRequest;
import com.miTienda.app.model.response.ClienteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClienteMapper {

    @Autowired
    private ProveedorMapper proveedorMapper;

    public ClienteEntity requestToEntity(ClienteRequest request){

        return ClienteEntity.builder()
                .nombre(request.getNombre().toUpperCase())
                .direccion(request.getDireccion().toUpperCase())
                .ciudad(request.getCiudad().toUpperCase())
                .dniCuit(request.getDniCuit())
                .tipoIva(request.getTipoIva().toUpperCase())
                .email(request.getEmail().toUpperCase())
                .telefono(request.getTelefono())
                .telefono1(request.getTelefono1())
                .saldo(request.getSaldo())
                .limiteCredito(request.getLimiteCredito())
                .observaciones(request.getObservaciones())
                .activo(request.isActivo())
                .timestamp(request.getTimestamp())
                .build();

    }

    public ClienteResponse entityToResponse(ClienteEntity entity){

    return ClienteResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .direccion(entity.getDireccion())
                .ciudad(entity.getCiudad())
                .dniCuit(entity.getDniCuit())
                .tipoIva(entity.getTipoIva())
                .email(entity.getEmail())
                .telefono(entity.getTelefono())
                .telefono1(entity.getTelefono1())
                .saldo(entity.getSaldo())
                .limiteCredito(entity.getLimiteCredito())
                .observaciones(entity.getObservaciones())
                .activo(entity.isActivo())
                .timestamp(entity.getTimestamp())
                .build();

    }

    public List<ClienteResponse> listEntityToListResponse(List<ClienteEntity> Clientees){
        List<ClienteResponse> responses = new ArrayList<>();
        for ( ClienteEntity Cliente: Clientees){
            responses.add(entityToResponse(Cliente));
        }
        return responses;
    }

    public ClienteEntity entityAndRequestToResponse(ClienteEntity entity, ClienteRequest request) {

        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);

        return ClienteEntity.builder()
                .id(request.getId())
                .nombre(request.getNombre().toUpperCase())
                .direccion(request.getDireccion().toUpperCase())
                .ciudad(request.getCiudad().toUpperCase())
                .dniCuit(request.getDniCuit())
                .tipoIva(request.getTipoIva().toUpperCase())
                .email(request.getEmail().toUpperCase())
                .telefono(request.getTelefono())
                .telefono1(request.getTelefono1())
                .saldo(request.getSaldo())
                .limiteCredito(request.getLimiteCredito())
                .observaciones(request.getObservaciones())
                .activo(request.isActivo())
                .timestamp(timestamp)
                .build();

    }

}