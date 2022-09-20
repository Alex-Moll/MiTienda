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
public class ClientRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @NotNull(message = "El campo name no puede ser Nulo")
    @NotBlank(message = "El campo name no puede estar en Blanco")
    @NotEmpty(message = "El campo name no puede estar Vacio")
    private String name;

    @NotNull(message = "El campo adress no puede ser Nulo")
    @NotBlank(message = "El campo adress no puede estar en Blanco")
    @NotEmpty(message = "El campo adress no puede estar Vacio")
    private String adress;

    private String city;

    private String dniCuit;

    private String tipoIva;

    private String email;

    @NotNull(message = "El campo phone no puede ser Nulo")
    @NotBlank(message = "El campo phone no puede estar en Blanco")
    @NotEmpty(message = "El campo phone no puede estar Vacio")
    private String phone;

    private String phone1;

    private Double saldo;

    private Double creditLimite;

    private String observations;

    private boolean active;

    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private boolean softDelete = false;
}
