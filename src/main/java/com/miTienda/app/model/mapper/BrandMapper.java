package com.miTienda.app.model.mapper;

import com.miTienda.app.model.entity.BrandEntity;
import com.miTienda.app.model.request.BrandRequest;
import com.miTienda.app.model.response.BrandResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BrandMapper {


    public BrandEntity requestToEntity(BrandRequest request){

        return BrandEntity.builder()
                .name(request.getName().toUpperCase())
                .build();

    }

    public BrandResponse entityToResponse(BrandEntity entity){

        return BrandResponse.builder()
                .id(entity.getId())
                .name(entity.getName().toUpperCase())
                .build();

    }

    public List<BrandResponse> listEntityToListResponse(List<BrandEntity> Marcas){
        List<BrandResponse> responses = new ArrayList<>();
        for ( BrandEntity Marca: Marcas){
            responses.add(entityToResponse(Marca));
        }
        return responses;
    }

    public BrandEntity entityAndRequestToResponse(BrandEntity entity, BrandRequest request) {

        entity.setName(request.getName().toUpperCase());

        return entity;

    }
}
