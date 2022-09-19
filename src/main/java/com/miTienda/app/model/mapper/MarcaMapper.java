package com.miTienda.app.model.mapper;

import com.miTienda.app.model.entity.MarcaEntity;
import com.miTienda.app.model.request.MarcaRequest;
import com.miTienda.app.model.response.MarcaResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MarcaMapper {


    public MarcaEntity requestToEntity(MarcaRequest request){

        return MarcaEntity.builder()
                .nombre(request.getNombre().toUpperCase())
                .build();

    }

    public MarcaResponse entityToResponse(MarcaEntity entity){

        return MarcaResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre().toUpperCase())
                .build();

    }

    public List<MarcaResponse> listEntityToListResponse(List<MarcaEntity> Marcas){
        List<MarcaResponse> responses = new ArrayList<>();
        for ( MarcaEntity Marca: Marcas){
            responses.add(entityToResponse(Marca));
        }
        return responses;
    }

    public MarcaEntity entityAndRequestToResponse(MarcaEntity entity, MarcaRequest request) {

        entity.setNombre(request.getNombre().toUpperCase());

        return entity;

    }
}
