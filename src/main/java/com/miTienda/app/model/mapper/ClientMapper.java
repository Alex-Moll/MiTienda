package com.miTienda.app.model.mapper;


import com.miTienda.app.model.entity.ClientEntity;
import com.miTienda.app.model.request.ClientRequest;
import com.miTienda.app.model.response.ClientResponse;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClientMapper {

    public ClientEntity requestToEntity(ClientRequest request){

        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);

        return ClientEntity.builder()
                .name(request.getName().toUpperCase())
                .adress(request.getAdress().toUpperCase())
                .city(request.getCity().toUpperCase())
                .dniCuit(request.getDniCuit())
                .tipoIva(request.getTipoIva().toUpperCase())
                .email(request.getEmail().toUpperCase())
                .phone(request.getPhone())
                .phone1(request.getPhone1())
                .saldo(request.getSaldo())
                .creditLimite(request.getCreditLimite())
                .observations(request.getObservations())
                .active(request.isActive())
                .timestamp(timestamp)
                .build();

    }

    public ClientResponse entityToResponse(ClientEntity entity){

    return ClientResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .adress(entity.getAdress())
                .city(entity.getCity())
                .dniCuit(entity.getDniCuit())
                .tipoIva(entity.getTipoIva())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .phone1(entity.getPhone1())
                .saldo(entity.getSaldo())
                .creditLimite(entity.getCreditLimite())
                .observations(entity.getObservations())
                .active(entity.isActive())
                .timestamp(entity.getTimestamp())
                .build();

    }

    public List<ClientResponse> listEntityToListResponse(List<ClientEntity> Clientees){
        List<ClientResponse> responses = new ArrayList<>();
        for ( ClientEntity Cliente: Clientees){
            responses.add(entityToResponse(Cliente));
        }
        return responses;
    }

    public ClientEntity entityAndRequestToResponse(ClientEntity entity, ClientRequest request) {

        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);

        return ClientEntity.builder()
                .id(request.getId())
                .name(request.getName().toUpperCase())
                .adress(request.getAdress().toUpperCase())
                .city(request.getCity().toUpperCase())
                .dniCuit(request.getDniCuit())
                .tipoIva(request.getTipoIva().toUpperCase())
                .email(request.getEmail().toUpperCase())
                .phone(request.getPhone())
                .phone1(request.getPhone1())
                .saldo(request.getSaldo())
                .creditLimite(request.getCreditLimite())
                .observations(request.getObservations())
                .active(request.isActive())
                .timestamp(timestamp)
                .build();

    }

}