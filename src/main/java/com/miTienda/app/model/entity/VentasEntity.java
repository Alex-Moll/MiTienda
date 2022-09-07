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

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE ventas SET soft_delete = true WHERE id = ?")
@Where(clause = "soft_delete = false")
@Table(name = "ventas")
public class VentasEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ventas_id")
    private Long id;

    private Long ticket;

    @OneToOne
    @JoinColumn(name = "cliente_client_id")
    private ClienteEntity cliente;

    private formaPagoEnum formaPago;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "articulo_Id", insertable = false, updatable = false)
    private ArticuloEntity articulo;

    @NotNull(message = "El campo cantidad no puede ser Nulo")
    @NotBlank(message = "El campo cantidad no puede estar en Blanco")
    @NotEmpty(message = "El campo cantidad no puede estar Vacio")
    private double cantidad;

    private double descuento;

    private double recargo;

    private double importe;

    private double subtotal;

    private double descuento2;

    private double iva;

    private double total;

    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private boolean softDelete = false;
}
