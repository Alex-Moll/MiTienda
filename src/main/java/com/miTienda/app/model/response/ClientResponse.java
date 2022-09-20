package com.miTienda.app.model.response;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientResponse {

    private Long id;
    private String name;
    private String adress;
    private String city;
    private String dniCuit;
    private String tipoIva;
    private String email;
    private String phone;
    private String phone1;
    private Double saldo;
    private Double creditLimite;
    private String observations;
    private boolean active;
    private Timestamp timestamp;
    private boolean softDelete = false;

}
