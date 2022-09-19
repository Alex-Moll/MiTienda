package com.miTienda.app.model.response;

import com.miTienda.app.model.entity.ArticuloEntity;
import lombok.Builder;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProveedorResponse {

    private Long id;
    private String razonSocial;
    private String domicilio;
    private String cuit;
    private String ciudad;
    private String iibb;
    private String telefono;
    private String telefono1;
    private double saldo;
    private Timestamp timestamp;
    private boolean softDelete = false;

}
