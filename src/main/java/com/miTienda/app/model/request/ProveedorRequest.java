package com.miTienda.app.model.request;

import com.miTienda.app.model.entity.ArticuloEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
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
public class ProveedorRequest {

    private Long Id;

    @NotNull(message = "El campo razonSocial no puede ser Nulo")
    @NotBlank(message = "El campo razonSocial no puede estar en Blanco")
    @NotEmpty(message = "El campo razonSocial no puede estar Vacio")
    @ApiModelProperty( notes = "Razon Social del Proveedor", example = "Proveedor-1", required = true)
    private String razonSocial;

    @Nullable
    @ApiModelProperty( notes = "domicilio del Proveedor", example = "Av. jujuy 1234")
    private String domicilio;

    @Nullable
    @ApiModelProperty( notes = "Cuit del Proveedor", example = "88-888888888-8")
    private String cuit;

    private String ciudad;

    private String iibb;

    private String telefono;

    private String telefono1;

    private double saldo;

    @CreationTimestamp
    private Timestamp timestamp;

}
