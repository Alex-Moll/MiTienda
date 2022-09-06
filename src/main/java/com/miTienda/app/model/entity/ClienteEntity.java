package com.miTienda.app.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
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
@SQLDelete(sql = "UPDATE clients SET soft_delete = true WHERE id = ?")
@Where(clause = "soft_delete = false")
@Table(name = "clients")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cliente_Id")
    private Long id;

    @NotNull(message = "El campo nombre no puede ser Nulo")
    @NotBlank(message = "El campo nombre no puede estar en Blanco")
    @NotEmpty(message = "El campo nombre no puede estar Vacio")
    private String nombre;

    @NotNull(message = "El campo apellido no puede ser Nulo")
    @NotBlank(message = "El campo apellido no puede estar en Blanco")
    @NotEmpty(message = "El campo apellido no puede estar Vacio")
    private String apellido;
    
    private String direccion;
    
    private String dni_cuit;

    private String iva;
    
    private String email;
    
    private String telefono;

    private double saldo;
    
    private double limiteCredito;
    
    private List<String> notificaciones;
    
    private boolean activo;

    @CreationTimestamp
    @Column(name = "comment_date")
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private boolean softDelete = false;
    
    
    
    
    
}
