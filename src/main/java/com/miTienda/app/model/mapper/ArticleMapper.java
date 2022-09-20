package com.miTienda.app.model.mapper;

import com.miTienda.app.model.entity.ArticleEntity;
import com.miTienda.app.model.request.ArticleRequest;
import com.miTienda.app.model.response.ArticleResponse;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleMapper {

    public ArticleEntity requestToEntity(ArticleRequest request){

        Double margen = (request.getPriceSale() / request.getPriceCost()) * 100;
        margen = (Math.round(margen*100.0)/100.0)-100;

        return ArticleEntity.builder()
                .code(request.getCode())
                .category(request.getCategory())
                .details(request.getDetails().toUpperCase())
                .brand(request.getBrand())
                .providers(request.getProviders())
                .provider_id(request.getProvider_id())
                .image(request.getImage())
                .color(request.getColor().toUpperCase())
                .measure(request.getMeasure().toUpperCase())
                .stock(request.getStock())
                .stockMin(request.getStockMin())
                .priceCost(request.getPriceCost())
                .margin(margen)
                .priceSale(request.getPriceSale())
                .timestamp(request.getTimestamp())
                .build();
    }

    public ArticleResponse entityToResponse(ArticleEntity entity){

        return ArticleResponse.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .category(entity.getCategory())
                .details(entity.getDetails())
                .brand(entity.getBrand())
                .provider_id(entity.getProvider_id())
                .providers(entity.getProviders())
                .image(entity.getImage())
                .color(entity.getColor())
                .measure(entity.getMeasure())
                .stock(entity.getStock())
                .stockMin(entity.getStockMin())
                .priceCost(entity.getPriceCost())
                .margin(entity.getMargin())
                .priceSale(entity.getPriceSale())
                .timestamp(entity.getTimestamp())
                .build();
    }

    public List<ArticleResponse> listEntityToListResponse(List<ArticleEntity> Articuloes){
        List<ArticleResponse> responses = new ArrayList<>();
        for ( ArticleEntity articulo: Articuloes){
            responses.add(entityToResponse(articulo));
        }
        return responses;
    }

    public ArticleEntity entityAndRequestToResponse(ArticleEntity entity, ArticleRequest request) {

        Double margen = (request.getPriceSale() / request.getPriceCost()) * 100;
        margen = (Math.round(margen*100.0)/100.0)-100;

        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);

        System.out.println("\n entity : " + entity.getCode());

        return ArticleEntity.builder()
                .id(request.getId())
                .code(request.getCode())
                .category(request.getCategory())
                .details(request.getDetails().toUpperCase())
                .brand(request.getBrand())
                .providers(request.getProviders())
                .provider_id(request.getProvider_id())
                .image(request.getImage())
                .color(request.getColor().toUpperCase())
                .measure(request.getMeasure().toUpperCase())
                .stock(request.getStock())
                .stockMin(request.getStockMin())
                .priceCost(request.getPriceCost())
                .margin(margen)
                .priceSale(request.getPriceSale())
                .timestamp(timestamp)
                .build();
    }

    public ArticleEntity entityAndRequestToResponseByCode(ArticleEntity entity, ArticleRequest request) {

        Double margen = (request.getPriceSale() / request.getPriceCost()) * 100;
        margen = (Math.round(margen*100.0)/100.0)-100;

        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);

        request.setId(entity.getId());

        return ArticleEntity.builder()
                .id(request.getId())
                .code(request.getCode())
                .category(request.getCategory())
                .details(request.getDetails().toUpperCase())
                .brand(request.getBrand())
                .providers(request.getProviders())
                .provider_id(request.getProvider_id())
                .image(request.getImage())
                .color(request.getColor().toUpperCase())
                .measure(request.getMeasure().toUpperCase())
                .stock(request.getStock())
                .stockMin(request.getStockMin())
                .priceCost(request.getPriceCost())
                .margin(margen)
                .priceSale(request.getPriceSale())
                .timestamp(timestamp)
                .build();


    }

}