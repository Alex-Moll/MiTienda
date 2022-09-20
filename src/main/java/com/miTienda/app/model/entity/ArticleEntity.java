package com.miTienda.app.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@SQLDelete(sql = "UPDATE articles SET soft_delete = true WHERE article_id = ?")
@Where(clause = "soft_delete = false")
@Table(name = "articles")
public class ArticleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @NotNull(message = "El campo code no puede ser Nulo")
    @NotBlank(message = "El campo code no puede estar en Blanco")
    @NotEmpty(message = "El campo code no puede estar Vacio")
    private String code;

    @OneToOne
    @JoinColumn(name = "category")
    private CategoryEntity category;
    
    @NotNull(message = "El campo details no puede ser Nulo")
    @NotBlank(message = "El campo details no puede estar en Blanco")
    @NotEmpty(message = "El campo details no puede estar Vacio")
    private String details;

    @OneToOne
    @JoinColumn(name = "brand")
    private BrandEntity brand;

    @JsonIgnore
    @ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.MERGE})
    @JoinTable(name="article_provider",
            joinColumns = @JoinColumn(name="article_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="provider_id", nullable = false))
    private List<ProviderEntity> providers = new ArrayList<>();

    private String provider_id;

    private String image;

    private String color;

    private String measure;

    private Double stock;

    private Double stockMin;

    private Double priceCost;

    private Double margin;

    private Double priceSale;

    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private boolean softDelete = false;

}
