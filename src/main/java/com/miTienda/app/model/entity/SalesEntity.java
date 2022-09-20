package com.miTienda.app.model.entity;

import com.miTienda.app.model.enumeraciones.formaPagoEnum;
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

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE sales SET soft_delete = true WHERE sales_id = ?")
@Where(clause = "soft_delete = false")
@Table(name = "sales")
public class SalesEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_id")
    private Long id;

    private Long ticket;

    @OneToOne
    @JoinColumn(name = "cliente_client_id")
    private ClientEntity cliente;

    private formaPagoEnum formaPago;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "articulo_Id", insertable = false, updatable = false)
    private ArticleEntity articulo;

    @NotNull(message = "El campo cantidad no puede ser Nulo")
    @NotBlank(message = "El campo cantidad no puede estar en Blanco")
    @NotEmpty(message = "El campo cantidad no puede estar Vacio")
    private Double cantidad;

    private Double descuento;

    private Double recargo;

    private Double importe;

    private Double subtotal;

    private Double descuento2;

    private Double iva;

    private Double total;

    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private boolean softDelete = false;
}
