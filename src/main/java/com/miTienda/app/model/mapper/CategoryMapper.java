package com.miTienda.app.model.mapper;

import com.miTienda.app.model.entity.CategoryEntity;
import com.miTienda.app.model.request.CategoryRequest;
import com.miTienda.app.model.response.CategoryResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {
    
    public CategoryEntity requestToEntity(CategoryRequest request){

        return CategoryEntity.builder()
                .name(request.getName().toUpperCase())
                .build();

    }

    public CategoryResponse entityToResponse(CategoryEntity entity){

        return CategoryResponse.builder()
                .id(entity.getId())
                .name(entity.getName().toUpperCase())
                .build();

    }

    public List<CategoryResponse> listEntityToListResponse(List<CategoryEntity> categorias){
        List<CategoryResponse> responses = new ArrayList<>();
        for ( CategoryEntity categoria: categorias){
            responses.add(entityToResponse(categoria));
        }
        return responses;
    }

    public CategoryEntity entityAndRequestToResponse(CategoryEntity entity, CategoryRequest request) {

        return CategoryEntity.builder()
                .id(request.getId())
                .name(request.getName().toUpperCase())
                .build();

    }
}
