package com.pragma.plazoletamicroservicio.domain.spi;

import com.pragma.plazoletamicroservicio.domain.model.EstadoPedido;
import com.pragma.plazoletamicroservicio.domain.model.Pedido;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PedidoEntity;

import java.util.List;


public interface IPedidoPersistencePort {

    PedidoEntity savePedido(Pedido pedido);

    boolean existsByIdClienteAndEstadoPedidoIn(Long idCliente, List<EstadoPedido> estados);




}
