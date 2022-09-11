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
    private String nombre;
    private String domicilio;
    private String cuit;
    private double saldo;
    private Timestamp timestamp;
    private boolean softDelete = false;

}
