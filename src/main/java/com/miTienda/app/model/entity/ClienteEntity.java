package com.miTienda.app.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE clientes SET soft_delete = true WHERE cliente_id = ?")
@Where(clause = "soft_delete = false")
@Table(name = "clientes")
public class ClienteEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public ClienteEntity(String nombre, String direccion, String ciudad, String dniCuit, String tipoIva, String email, String telefono, String telefono1, double saldo, double limiteCredito, String observaciones, boolean activo, Timestamp timestamp, boolean softDelete) {
    }
}
