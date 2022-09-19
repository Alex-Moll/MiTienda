package com.miTienda.app.model.request;

import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Clase que representa a los Clientes")
public class ClienteRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cliente_id")
    private Long id;

    @NotNull(message = "El campo nombre no puede ser Nulo")
    @NotBlank(message = "El campo nombre no puede estar en Blanco")
    @NotEmpty(message = "El campo nombre no puede estar Vacio")
    private String nombre;

    @NotNull(message = "El campo direccion no puede ser Nulo")
    @NotBlank(message = "El campo direccion no puede estar en Blanco")
    @NotEmpty(message = "El campo direccion no puede estar Vacio")
    private String direccion;

    private String ciudad;

    private String dniCuit;

    private String tipoIva;

    private String email;

    @NotNull(message = "El campo telefono no puede ser Nulo")
    @NotBlank(message = "El campo telefono no puede estar en Blanco")
    @NotEmpty(message = "El campo telefono no puede estar Vacio")
    private String telefono;

    private String telefono1;

    private Double saldo;

    private Double limiteCredito;

    private String observaciones;

    private boolean activo;

    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private boolean softDelete = false;

}
