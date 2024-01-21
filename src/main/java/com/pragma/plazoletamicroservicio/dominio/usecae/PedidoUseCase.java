package com.pragma.plazoletamicroservicio.dominio.usecae;

import com.pragma.plazoletamicroservicio.dominio.api.IPedidoServicePort;
import com.pragma.plazoletamicroservicio.dominio.model.Pedido;
import com.pragma.plazoletamicroservicio.dominio.spi.IPedidoPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PedidoUseCase implements IPedidoServicePort {

    private final IPedidoPersistencePort pedidoPersistencePort;

    @Override
    public void savePedido(Pedido pedido) {
        pedidoPersistencePort.savePedido(pedido);
    }

    @Override
    public List<Pedido> getAllPedidos() {
        return pedidoPersistencePort.getAllPedidos();
    }

    @Override
    public Pedido getPedidoById(Long id) {
        return pedidoPersistencePort.getPedidoById(id);
    }

    @Override
    public void updatePedido(Pedido pedido) {
        pedidoPersistencePort.updatePedido(pedido);
    }

    @Override
    public void deletePedido(Long id) {
        pedidoPersistencePort.deletePedido(id);
    }
}
