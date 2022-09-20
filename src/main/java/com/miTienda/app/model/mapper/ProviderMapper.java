package com.miTienda.app.model.mapper;

import com.miTienda.app.model.entity.ProviderEntity;
import com.miTienda.app.model.request.ProviderRequest;
import com.miTienda.app.model.response.ProviderResponse;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProviderMapper {

    public ProviderEntity requestToEntity(ProviderRequest request){

        return ProviderEntity.builder()
                .businessName(request.getBusinessName().toUpperCase())
                .adress(request.getAdress().toUpperCase())
                .city(request.getCity().toUpperCase())
                .iibb(request.getIibb())
                .phone(request.getPhone())
                .phone1(request.getPhone1())
                .cuit(request.getCuit())
                .balance(request.getBalance())
                .timestamp(request.getTimestamp())
                .build();

    }

    public ProviderResponse entityToResponse(ProviderEntity entity){

        return ProviderResponse.builder()
                .id(entity.getId())
                .businessName(entity.getBusinessName().toUpperCase())
                .adress(entity.getAdress().toUpperCase())
                .cuit(entity.getCuit())
                .city(entity.getCity().toUpperCase())
                .iibb(entity.getIibb())
                .phone(entity.getPhone())
                .phone1(entity.getPhone1())
                .balance(entity.getBalance())
                .timestamp(entity.getTimestamp())
                .build();

    }

    public List<ProviderResponse> listEntityToListResponse(List<ProviderEntity> proveedores){
        List<ProviderResponse> responses = new ArrayList<>();
        for ( ProviderEntity proveedor: proveedores){
            responses.add(entityToResponse(proveedor));
        }
        return responses;
    }

    public ProviderEntity entityAndRequestToResponse(ProviderEntity entity, ProviderRequest request) {

        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);

        return ProviderEntity.builder()
                .id(request.getId())
                .businessName(request.getBusinessName().toUpperCase())
                .adress(request.getAdress().toUpperCase())
                .city(request.getCity().toUpperCase())
                .iibb(request.getIibb())
                .phone(request.getPhone())
                .phone1(request.getPhone1())
                .cuit(request.getCuit())
                .balance(request.getBalance())
                .timestamp(timestamp)
                .build();

    }

}

