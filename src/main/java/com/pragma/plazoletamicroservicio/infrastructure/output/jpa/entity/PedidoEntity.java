package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity;

import com.pragma.plazoletamicroservicio.domain.model.EstadoPedido;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class PedidoEntity {

    private Long id;

    private Long idCliente;

    private LocalDateTime fecha;


    @Enumerated(EnumType.STRING)
    private EstadoPedido estadoPedido;

    private Long idChef;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private RestauranteEntity restaurante;


}
