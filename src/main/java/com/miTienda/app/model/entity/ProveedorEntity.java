package com.miTienda.app.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Setter
@Getter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE proveedores SET soft_delete = true WHERE proveedor_id = ?")
@Where(clause = "soft_delete = false")
@Table(name = "proveedores")
public class ProveedorEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proveedor_id")
    private Long id;

    @NotNull(message = "El campo razonSocial no puede ser Nulo")
    @NotBlank(message = "El campo razonSocial no puede estar en Blanco")
    @NotEmpty(message = "El campo razonSocial no puede estar Vacio")
    private String razonSocial;
    
    private String domicilio;

    private String ciudad;

    private String cuit;

    private String iibb;

    private String telefono;

    private String telefono1;
    
    private Double saldo;

//    @JsonBackReference
    @JsonIgnore
    @ManyToMany (mappedBy = "proveedores", cascade= {CascadeType.MERGE, CascadeType.PERSIST})
    private List<ArticuloEntity> articulos = new ArrayList();

    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private boolean softDelete = false;


    public ProveedorEntity(String razonSocial, String domicilio, String ciudad, String cuit, String iibb, String telefono, String telefono1, double saldo, Timestamp timestamp, boolean b) {
    }
}
