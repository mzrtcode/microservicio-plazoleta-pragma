package com.pragma.plazoletamicroservicio.domain.usecase;

import com.pragma.plazoletamicroservicio.domain.api.IPedidoServicePort;
import com.pragma.plazoletamicroservicio.domain.model.EstadoPedido;
import com.pragma.plazoletamicroservicio.domain.model.Pedido;
import com.pragma.plazoletamicroservicio.domain.spi.IPedidoPersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.TrazabilidadDto;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PedidoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Page<Pedido> listarPedidosPorRestauranteEmpleado(Long idRestaurante, EstadoPedido estadoPedido, Pageable pageable) {
        return pedidoPersistencePort.listarPedidosPorRestauranteEmpleado(idRestaurante, estadoPedido, pageable);
    }

    @Override
    public Optional<Pedido> obtenerPedidoPorId(Long idPedido) {
        return pedidoPersistencePort.obtenerPedidoPorId(idPedido);
    }

    @Override
    public void notificarUsuario(String destinatario, String mensaje) {
        pedidoPersistencePort.notificarUsuario(destinatario, mensaje);
    }

    @Override
    public void crearRegistroEstadoPedido(TrazabilidadDto trazabilidadDto) {
        pedidoPersistencePort.crearRegistroEstadoPedido(trazabilidadDto);
    }


}
