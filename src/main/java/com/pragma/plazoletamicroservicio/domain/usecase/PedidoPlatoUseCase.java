package com.pragma.plazoletamicroservicio.domain.usecase;

import com.pragma.plazoletamicroservicio.domain.api.IPedidoPlatoServicePort;
import com.pragma.plazoletamicroservicio.domain.model.Pedido;
import com.pragma.plazoletamicroservicio.domain.model.PedidoPlato;
import com.pragma.plazoletamicroservicio.domain.spi.IPedidoPlatoPersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PedidoPlatoEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PedidoPlatoUseCase implements IPedidoPlatoServicePort {

    private final IPedidoPlatoPersistencePort platoPersistencePort;

    @Override
    public void savePedidoPlato(PedidoPlato pedidoPlato) {
        platoPersistencePort.savePedidoPlato(pedidoPlato);
    }

    @Override
    public List<PedidoPlato> findByPedidoEntityId(Long idPedido) {
        return platoPersistencePort.findByPedidoEntityId(idPedido);
    }


}
