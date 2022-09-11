package com.miTienda.app.model.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE grupos SET soft_delete = true WHERE id = ?")
@Where(clause = "soft_delete = false")
@Table(name = "grupos")
public class TipoArticuloEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "tipo_articulo-id")
    private Long id;

    @NotNull(message = "El campo nombre no puede ser Nulo")
    @NotBlank(message = "El campo nombre no puede estar en Blanco")
    @NotEmpty(message = "El campo nombre no puede estar Vacio")
    private String nombre;


}
