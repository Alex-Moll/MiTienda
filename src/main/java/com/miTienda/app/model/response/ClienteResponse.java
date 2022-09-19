package com.miTienda.app.model.response;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteResponse {

    private Long id;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String dniCuit;
    private String tipoIva;
    private String email;
    private String telefono;
    private String telefono1;
    private Double saldo;
    private Double limiteCredito;
    private String observaciones;
    private boolean activo;
    private Timestamp timestamp;
    private boolean softDelete = false;

}
