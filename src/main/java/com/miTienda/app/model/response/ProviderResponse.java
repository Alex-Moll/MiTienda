package com.miTienda.app.model.response;

import com.miTienda.app.model.entity.ArticleEntity;
import lombok.Builder;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProviderResponse {

    private Long id;
    private String businessName;
    private String adress;
    private String city;
    private String cuit;
    private String iibb;
    private String phone;
    private String phone1;
    private Double balance;
    private List<ArticleEntity> articles = new ArrayList();
    private Timestamp timestamp;
    private boolean softDelete = false;

}
