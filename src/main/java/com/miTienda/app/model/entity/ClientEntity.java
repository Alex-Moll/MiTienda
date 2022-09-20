package com.miTienda.app.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
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
@SQLDelete(sql = "UPDATE clients SET soft_delete = true WHERE client_id = ?")
@Where(clause = "soft_delete = false")
@Table(name = "clients")
public class ClientEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @NotNull(message = "El campo name no puede ser Nulo")
    @NotBlank(message = "El campo name no puede estar en Blanco")
    @NotEmpty(message = "El campo name no puede estar Vacio")
    private String name;

    @NotNull(message = "El campo adress no puede ser Nulo")
    @NotBlank(message = "El campo adress no puede estar en Blanco")
    @NotEmpty(message = "El campo adress no puede estar Vacio")
    private String adress;

    private String city;
    
    private String dniCuit;

    private String tipoIva;
    
    private String email;

    @NotNull(message = "El campo phone no puede ser Nulo")
    @NotBlank(message = "El campo phone no puede estar en Blanco")
    @NotEmpty(message = "El campo phone no puede estar Vacio")
    private String phone;

    private String phone1;

    private Double saldo;
    
    private Double creditLimite;
    
    private String observations;
    
    private boolean active;

    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private boolean softDelete = false;

}
