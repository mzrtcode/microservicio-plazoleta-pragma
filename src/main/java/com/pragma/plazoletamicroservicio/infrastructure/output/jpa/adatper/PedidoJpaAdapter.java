package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.adatper;

import com.pragma.plazoletamicroservicio.domain.model.Pedido;
import com.pragma.plazoletamicroservicio.domain.spi.IPedidoPersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PedidoEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper.IPedidoMapper;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository.IPedidoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PedidoJpaAdapter implements IPedidoPersistencePort {

    private final IPedidoRepository pedidoRepository;
    private final IPedidoMapper pedidoMapper;
    @Override
    public PedidoEntity savePedido(Pedido pedido) {
        PedidoEntity pedidoEntity = pedidoMapper.toPedidoEntity(pedido);
        return pedidoRepository.save(pedidoEntity);
    }
}
