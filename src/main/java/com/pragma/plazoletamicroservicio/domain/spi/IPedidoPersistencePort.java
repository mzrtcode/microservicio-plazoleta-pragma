package com.pragma.plazoletamicroservicio.domain.spi;

import com.pragma.plazoletamicroservicio.domain.model.EstadoPedido;
import com.pragma.plazoletamicroservicio.domain.model.Pedido;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PedidoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface IPedidoPersistencePort {

    PedidoEntity savePedido(Pedido pedido);

    boolean existsByIdClienteAndEstadoPedidoIn(Long idCliente, List<EstadoPedido> estados);

    Page<Pedido> listarPedidosPorRestauranteEmpleado(Long idRestaurante, EstadoPedido estadoPedido, Pageable pageable);

    Optional<Pedido> obtenerPedidoPorId(Long idPedido);

    void notificarUsuario(String destinatario, String mensaje);


}
