package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity;

import com.pragma.plazoletamicroservicio.domain.model.Pedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedidos_platos")
public class PedidoPlatoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private PedidoEntity pedido;

    @ManyToOne
    @JoinColumn(name = "id_plato")
    private PlatoEntity plato;

    private Integer cantidad;
}
