package com.pragma.plazoletamicroservicio.domain.usecase;

import com.pragma.plazoletamicroservicio.domain.api.IPedidoServicePort;
import com.pragma.plazoletamicroservicio.domain.model.EstadoPedido;
import com.pragma.plazoletamicroservicio.domain.model.Pedido;
import com.pragma.plazoletamicroservicio.domain.spi.IPedidoPersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PedidoEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PedidoUseCase implements IPedidoServicePort {

    private final IPedidoPersistencePort pedidoPersistencePort;

    @Override
    public PedidoEntity savePedido(Pedido pedido) {
        return pedidoPersistencePort.savePedido(pedido);
    }

    @Override
    public boolean existsByIdClienteAndEstadoPedidoIn(Long idCliente, List<EstadoPedido> estados) {
        return pedidoPersistencePort.existsByIdClienteAndEstadoPedidoIn(idCliente, estados);
    }

}
