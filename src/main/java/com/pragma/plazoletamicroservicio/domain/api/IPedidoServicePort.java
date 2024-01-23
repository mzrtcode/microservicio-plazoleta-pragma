package com.pragma.plazoletamicroservicio.domain.api;

import com.pragma.plazoletamicroservicio.domain.model.Pedido;

import java.util.List;

public interface IPedidoServicePort {

    void savePedido(Pedido pedido);

    List<Pedido> getAllPedidos();

    Pedido getPedidoById(Long id);

    void updatePedido(Pedido pedido);

    void deletePedido(Long id);

}
