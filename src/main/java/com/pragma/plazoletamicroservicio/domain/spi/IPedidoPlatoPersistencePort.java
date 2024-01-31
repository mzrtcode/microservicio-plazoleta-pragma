package com.pragma.plazoletamicroservicio.domain.spi;

import com.pragma.plazoletamicroservicio.domain.model.PedidoPlato;

public interface IPedidoPlatoPersistencePort {

    void savePedidoPlato(PedidoPlato pedidoPlato);
}
