package com.miTienda.app.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE articles SET soft_delete = true WHERE id = ?")
@Where(clause = "soft_delete = false")
@Table(name = "articulos")
public class ArticuloEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "articulo_id")
    private Long id;

    @NotNull(message = "El campo codigo no puede ser Nulo")
    @NotBlank(message = "El campo codigo no puede estar en Blanco")
    @NotEmpty(message = "El campo codigo no puede estar Vacio")
    private String codigo;

    private String tipo;
    
    @NotNull(message = "El campo detalle no puede ser Nulo")
    @NotBlank(message = "El campo detalle no puede estar en Blanco")
    @NotEmpty(message = "El campo detalle no puede estar Vacio")
    private String detalle;

    private String marca;

    @ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.MERGE})
    @JoinTable(name="articulo_proveedor",
            joinColumns = @JoinColumn(name="articulo_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="proveedor_id", nullable = false))
    private Set<ProveedorEntity> proveedores;

    private String color;

    private String medida;

    private double stock;

    private double stockMin;

    private double precioCosto;

    private double ganancia;

    private double precioVenta;

    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private boolean softDelete = false;


}
