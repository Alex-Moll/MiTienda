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
@SQLDelete(sql = "UPDATE proveedores SET soft_delete = true WHERE id = ?")
@Where(clause = "soft_delete = false")
@Table(name = "proveedores")
public class ProveedorEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "proveedor_Id")
    private Long id;

    @NotNull(message = "El campo nombre no puede ser Nulo")
    @NotBlank(message = "El campo nombre no puede estar en Blanco")
    @NotEmpty(message = "El campo nombre no puede estar Vacio")
    private String nombre;
    
    private String domicilio;

    private String cuit;
    
    private double saldo;

    @CreationTimestamp
    @Column(name = "comment_date")
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private boolean softDelete = false;
    
    
}
