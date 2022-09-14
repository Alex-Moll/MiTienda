package com.miTienda.app.model.mapper;

import com.miTienda.app.model.entity.TipoArticuloEntity;
import com.miTienda.app.model.request.TipoArticuloRequest;
import com.miTienda.app.model.response.TipoArticuloResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TipoArticuloMapper {
    public TipoArticuloEntity requestToEntity(TipoArticuloRequest request){

        return TipoArticuloEntity.builder()
                .nombre(request.getNombre())
                .build();

    }

    public TipoArticuloResponse entityToResponse(TipoArticuloEntity entity){

        return TipoArticuloResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .build();

    }

    public List<TipoArticuloResponse> listEntityToListResponse(List<TipoArticuloEntity> tipoArticulos){
        List<TipoArticuloResponse> responses = new ArrayList<>();
        for ( TipoArticuloEntity tipoArticulo: tipoArticulos){
            responses.add(entityToResponse(tipoArticulo));
        }
        return responses;
    }

    public TipoArticuloEntity entityAndRequestToResponse(TipoArticuloEntity entity, TipoArticuloRequest request) {

        entity.setNombre(request.getNombre());

        return entity;

    }
}
