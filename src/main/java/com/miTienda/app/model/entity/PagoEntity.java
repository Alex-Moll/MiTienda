package com.miTienda.app.model.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE articles SET soft_delete = true WHERE id = ?")
@Where(clause = "soft_delete = false")
@Table(name = "pagos")
public class PagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "pago_id")
    private Long id;


}
