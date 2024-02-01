package com.pragma.plazoletamicroservicio.domain.usecase;

import com.pragma.plazoletamicroservicio.domain.api.IPedidoPlatoServicePort;
import com.pragma.plazoletamicroservicio.domain.model.PedidoPlato;
import com.pragma.plazoletamicroservicio.domain.spi.IPedidoPlatoPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PedidoPlatoUseCase implements IPedidoPlatoServicePort {

    private final IPedidoPlatoPersistencePort platoPersistencePort;

    @Override
    public void savePedidoPlato(PedidoPlato pedidoPlato) {
        platoPersistencePort.savePedidoPlato(pedidoPlato);
    }
}
