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
@SQLDelete(sql = "UPDATE categories SET soft_delete = true WHERE category_id = ?")
@Where(clause = "soft_delete = false")
@Table(name = "categories")
public class CategoryEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @NotNull(message = "El campo name no puede ser Nulo")
    @NotBlank(message = "El campo name no puede estar en Blanco")
    @NotEmpty(message = "El campo name no puede estar Vacio")
    private String name;

    @Column(name = "soft_delete")
    private boolean softDelete = false;

    public CategoryEntity(String name, boolean softDelete) {
    }
}
