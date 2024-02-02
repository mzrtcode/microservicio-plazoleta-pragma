package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.adatper;

import com.pragma.plazoletamicroservicio.domain.model.PedidoPlato;
import com.pragma.plazoletamicroservicio.domain.spi.IPedidoPlatoPersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PedidoPlatoEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper.IPedidoPlatoMapper;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository.IPedidoPlatoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PedidoPlatoJpaAdapter implements IPedidoPlatoPersistencePort {

    private final IPedidoPlatoRepository pedidoPlatoRepository;
    private final IPedidoPlatoMapper pedidoPlatoMapper;


    @Override
    public PedidoPlatoEntity savePedidoPlato(PedidoPlato pedidoPlato) {
        PedidoPlatoEntity pedidoPlatoEntity = pedidoPlatoMapper.toPedidoPlatoEntity(pedidoPlato);
           return pedidoPlatoRepository.save(pedidoPlatoEntity);
    }

    @Override
    public List<PedidoPlato> findByPedidoEntityId(Long idPedido) {
        List<PedidoPlatoEntity> pedidoPlatoList = pedidoPlatoRepository.findByPedidoEntityId(idPedido);
        List<PedidoPlato> pedidoPlato = pedidoPlatoMapper.toPedidoPlato(pedidoPlatoList);
        return pedidoPlato;
    }


}
