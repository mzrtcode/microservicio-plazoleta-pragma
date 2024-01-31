package com.pragma.plazoletamicroservicio.domain.spi;

import com.pragma.plazoletamicroservicio.domain.model.PedidoPlato;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PedidoPlatoEntity;

public interface IPedidoPlatoPersistencePort {

    PedidoPlatoEntity savePedidoPlato(PedidoPlato pedidoPlato);
}