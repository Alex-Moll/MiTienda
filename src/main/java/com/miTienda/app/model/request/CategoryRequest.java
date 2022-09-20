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
@ApiModel(description = "Clase que representa a los Tipo de Articulos")
public class CategoryRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_id")
    private Long id;

    @NotNull(message = "El campo nombre no puede ser Nulo")
    @NotBlank(message = "El campo nombre no puede estar en Blanco")
    @NotEmpty(message = "El campo nombre no puede estar Vacio")
    @ApiModelProperty( notes = "Descripcion del Tipo de Articulo", example = "Remeras/Correas/Herramientas", required = true)
    private String name;

    @Column(name = "soft_delete")
    private boolean softDelete = false;
}
