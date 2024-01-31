package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedidos_platos")
public class PedidoPlatoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private PedidoEntity pedidoEntity;

    @ManyToOne
    @JoinColumn(name = "id_plato")
    private PlatoEntity platoEntity;

    private String cantidad;
}
