package com.miTienda.app.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miTienda.app.model.entity.ArticleEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Clase que representa a los Proveedores")
public class ProviderRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_id")
    private Long id;

    @NotNull(message = "El campo businessName no puede ser Nulo")
    @NotBlank(message = "El campo businessName no puede estar en Blanco")
    @NotEmpty(message = "El campo businessName no puede estar Vacio")
    private String businessName;

    private String adress;

    private String city;

    private String cuit;

    private String iibb;

    private String phone;

    private String phone1;

    private Double balance;

    @JsonIgnore
    @ManyToMany(mappedBy = "providers", cascade= {CascadeType.MERGE, CascadeType.PERSIST})
    private List<ArticleEntity> articles = new ArrayList();

    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private boolean softDelete = false;

}
