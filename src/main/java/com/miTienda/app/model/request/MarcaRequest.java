package com.miTienda.app.model.request;

import com.miTienda.app.model.entity.MarcaEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Clase que representa a las Marcas")
public class MarcaRequest {

    private Long Id;

    @NotNull(message = "El campo nombre no puede ser Nulo")
    @NotBlank(message = "El campo nombre no puede estar en Blanco")
    @NotEmpty(message = "El campo nombre no puede estar Vacio")
    @ApiModelProperty( notes = "Nombre de la Marca", example = "Levis/TDH/renault", required = true)
    private String nombre;

}
