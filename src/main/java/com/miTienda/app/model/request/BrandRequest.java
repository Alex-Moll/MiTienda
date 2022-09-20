package com.miTienda.app.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Clase que representa a las Marcas")
public class BrandRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long id;

    @NotNull(message = "El campo nombre no puede ser Nulo")
    @NotBlank(message = "El campo nombre no puede estar en Blanco")
    @NotEmpty(message = "El campo nombre no puede estar Vacio")
    private String name;

}
