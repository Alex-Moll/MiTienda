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
@SQLDelete(sql = "UPDATE talles SET soft_delete = true WHERE id = ?")
@Where(clause = "soft_delete = false")
@Table(name = "talles")
public class TalleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "talle_id")
    private Long id;

    @NotNull(message = "El campo talle no puede ser Nulo")
    @NotBlank(message = "El campo talle no puede estar en Blanco")
    @NotEmpty(message = "El campo talle no puede estar Vacio")
    private String talle;

    private String cantidad;




}
