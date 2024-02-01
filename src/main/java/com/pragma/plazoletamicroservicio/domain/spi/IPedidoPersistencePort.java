package com.pragma.plazoletamicroservicio.domain.spi;

import com.pragma.plazoletamicroservicio.domain.model.EstadoPedido;
import com.pragma.plazoletamicroservicio.domain.model.Pedido;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PedidoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IPedidoPersistencePort {

    PedidoEntity savePedido(Pedido pedido);

    boolean existsByIdClienteAndEstadoPedidoIn(Long idCliente, List<EstadoPedido> estados);

    Page<Pedido> findByEstadoPedidoAndIdChef(Long idChef, EstadoPedido estadoPedido, Pageable pageable);


}
