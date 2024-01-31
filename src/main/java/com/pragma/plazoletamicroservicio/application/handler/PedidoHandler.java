package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.PedidoRequest;
import com.pragma.plazoletamicroservicio.application.exception.PedidoInvalidException;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;

public interface PedidoHandler {

    void crearPedidoInDB(PedidoRequest pedidoRequest) throws PedidoInvalidException, RestauranteNotFoundException;
}
