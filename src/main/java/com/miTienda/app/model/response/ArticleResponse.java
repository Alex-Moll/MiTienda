package com.miTienda.app.model.response;

import com.miTienda.app.model.entity.BrandEntity;
import com.miTienda.app.model.entity.ProviderEntity;
import com.miTienda.app.model.entity.CategoryEntity;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleResponse {

    private Long id;
    private String code;
    private CategoryEntity category;
    private String details;
    private BrandEntity brand;
    private List<ProviderEntity> providers = new ArrayList<>();
    private String provider_id;
    private String image;
    private String color;
    private String measure;
    private Double stock;
    private Double stockMin;
    private Double priceCost;
    private Double margin;
    private Double priceSale;
    private Timestamp timestamp;
    private boolean softDelete = false;

}
