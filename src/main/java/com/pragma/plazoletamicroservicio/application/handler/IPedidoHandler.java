package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.ActualizarPedidoRequest;
import com.pragma.plazoletamicroservicio.application.dto.PedidoRequest;
import com.pragma.plazoletamicroservicio.application.dto.PedidoResponse;
import com.pragma.plazoletamicroservicio.application.exception.PedidoInvalidException;
import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import com.pragma.plazoletamicroservicio.domain.model.EstadoPedido;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;

import java.util.List;

public interface IPedidoHandler {

    void crearPedidoInDB(PedidoRequest pedidoRequest) throws PedidoInvalidException, RestauranteNotFoundException, PlatoNoExiste;
    PedidoResponse listarPedidosPorRestauranteEmpleado(EstadoPedido estadoPedido, int pageNo, int pageSize) throws PedidoInvalidException;

    void actualizarPedido(Long idPedido, ActualizarPedidoRequest actualizarPedidoRequest) throws PedidoInvalidException;

}
