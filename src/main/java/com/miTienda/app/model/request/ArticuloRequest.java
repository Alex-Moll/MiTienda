package com.miTienda.app.model.request;

import com.miTienda.app.model.entity.MarcaEntity;
import com.miTienda.app.model.entity.ProveedorEntity;
import com.miTienda.app.model.entity.CategoriaEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Clase que representa a los Articulos")
public class ArticuloRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "articulo_id")
    private Long id;

    @NotNull(message = "El campo codigo no puede ser Nulo")
    @NotBlank(message = "El campo codigo no puede estar en Blanco")
    @NotEmpty(message = "El campo codigo no puede estar Vacio")
    private String codigo;

    @OneToOne
    @JoinColumn(name = "familia")
    private CategoriaEntity categoria;

    @NotNull(message = "El campo detalle no puede ser Nulo")
    @NotBlank(message = "El campo detalle no puede estar en Blanco")
    @NotEmpty(message = "El campo detalle no puede estar Vacio")
    private String detalle;

    @OneToOne
    @JoinColumn(name = "marca")
    private MarcaEntity marca;

    @ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.MERGE})
    @JoinTable(name="articulo_proveedor",
            joinColumns = @JoinColumn(name="articulo_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="proveedor_id", nullable = false))
    private List<ProveedorEntity> proveedores = new ArrayList<>();

    private String proveedor_id;

    private String imagen;

    private String color;

    private String medida;

    private double stock;

    private double stockMin;

    private double precioCosto;

    private double margen;

    private double precioVenta;

    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private boolean softDelete = false;

}
