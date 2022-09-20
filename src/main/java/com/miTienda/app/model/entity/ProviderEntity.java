package com.miTienda.app.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@SQLDelete(sql = "UPDATE providers SET soft_delete = true WHERE provider_id = ?")
@Where(clause = "soft_delete = false")
@Table(name = "providers")
public class ProviderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_id")
    private Long id;

    @NotNull(message = "El campo businessName no puede ser Nulo")
    @NotBlank(message = "El campo businessName no puede estar en Blanco")
    @NotEmpty(message = "El campo businessName no puede estar Vacio")
    private String businessName;
    
    private String adress;

    private String city;

    private String cuit;

    private String iibb;

    private String phone;

    private String phone1;
    
    private Double balance;

    @JsonIgnore
    @ManyToMany (mappedBy = "providers", cascade= {CascadeType.MERGE, CascadeType.PERSIST})
    private List<ArticleEntity> articles = new ArrayList();

    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private boolean softDelete = false;

}
