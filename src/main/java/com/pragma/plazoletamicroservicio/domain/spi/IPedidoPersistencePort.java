package com.pragma.plazoletamicroservicio.domain.spi;

import com.pragma.plazoletamicroservicio.domain.model.Pedido;

import java.util.List;

public interface IPedidoPersistencePort {

    void savePedido(Pedido pedido);

}
