package com.miTienda.app.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE articulos SET soft_delete = true WHERE articulo_id = ?")
@Where(clause = "soft_delete = false")
@Table(name = "articulos")
public class ArticuloEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "articulo_id")
    private Long id;

    @NotNull(message = "El campo codigo no puede ser Nulo")
    @NotBlank(message = "El campo codigo no puede estar en Blanco")
    @NotEmpty(message = "El campo codigo no puede estar Vacio")
    private String codigo;

    @OneToOne
    @JoinColumn(name = "categoria")
    private CategoriaEntity categoria;
    
    @NotNull(message = "El campo detalle no puede ser Nulo")
    @NotBlank(message = "El campo detalle no puede estar en Blanco")
    @NotEmpty(message = "El campo detalle no puede estar Vacio")
    private String detalle;

    @OneToOne
    @JoinColumn(name = "marca")
    private MarcaEntity marca;

//    @JsonManagedReference
    @JsonIgnore
    @ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.MERGE})
    @JoinTable(name="articulo_proveedor",
            joinColumns = @JoinColumn(name="articulo_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="proveedor_id", nullable = false))
    private List<ProveedorEntity> proveedores = new ArrayList<>();

    private String proveedor_id;

    private String imagen;

    private String color;

    private String medida;

    private Double stock;

    private Double stockMin;

    private Double precioCosto;

    private Double margen;

    private Double precioVenta;

    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private boolean softDelete = false;

}
