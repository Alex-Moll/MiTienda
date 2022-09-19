package com.miTienda.app.model.mapper;

import com.miTienda.app.model.entity.CategoriaEntity;
import com.miTienda.app.model.request.CategoriaRequest;
import com.miTienda.app.model.request.CategoriaRequest;
import com.miTienda.app.model.response.CategoriaResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoriaMapper {
    
    public CategoriaEntity requestToEntity(CategoriaRequest request){

        return CategoriaEntity.builder()
                .nombre(request.getNombre().toUpperCase())
                .build();

    }

    public CategoriaResponse entityToResponse(CategoriaEntity entity){

        return CategoriaResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre().toUpperCase())
                .build();

    }

    public List<CategoriaResponse> listEntityToListResponse(List<CategoriaEntity> categorias){
        List<CategoriaResponse> responses = new ArrayList<>();
        for ( CategoriaEntity categoria: categorias){
            responses.add(entityToResponse(categoria));
        }
        return responses;
    }

    public CategoriaEntity entityAndRequestToResponse(CategoriaEntity entity, CategoriaRequest request) {

        entity.setNombre(request.getNombre().toUpperCase());

        return entity;

    }
}
