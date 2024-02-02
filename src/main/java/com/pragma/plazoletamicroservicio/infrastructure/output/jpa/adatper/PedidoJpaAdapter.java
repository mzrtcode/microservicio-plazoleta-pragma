package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.adatper;

import com.pragma.plazoletamicroservicio.domain.model.EstadoPedido;
import com.pragma.plazoletamicroservicio.domain.model.Pedido;
import com.pragma.plazoletamicroservicio.domain.spi.IPedidoPersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PedidoEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper.IPedidoEntityMapper;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository.IPedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoJpaAdapter implements IPedidoPersistencePort {

    private final IPedidoRepository pedidoRepository;
    private final IPedidoEntityMapper pedidoMapper;
    @Override
    public PedidoEntity savePedido(Pedido pedido) {
        PedidoEntity pedidoEntity = pedidoMapper.toPedidoEntity(pedido);
        return pedidoRepository.save(pedidoEntity);
    }

    @Override
    public boolean existsByIdClienteAndEstadoPedidoIn(Long idCliente, List<EstadoPedido> estados) {
        return pedidoRepository.existsByIdClienteAndEstadoPedidoIn(idCliente, estados);
    }

    @Override
    public Page<Pedido> listarPedidosPorRestauranteEmpleado(Long idChef, EstadoPedido estadoPedido, Pageable pageable) {
        Page<PedidoEntity> entities = pedidoRepository.findByRestauranteIdAndEstadoPedido(idChef, estadoPedido, pageable);
        return  entities.map(pedidoMapper::toPedido);
    }

}
