package com.pragma.plazoletamicroservicio.domain.api;

import com.pragma.plazoletamicroservicio.domain.model.Pedido;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PedidoEntity;

import java.util.List;

public interface IPedidoServicePort {

    PedidoEntity savePedido(Pedido pedido);

}
