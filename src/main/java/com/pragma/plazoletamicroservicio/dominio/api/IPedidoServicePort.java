package com.pragma.plazoletamicroservicio.dominio.api;

import com.pragma.plazoletamicroservicio.dominio.model.Pedido;

import java.util.List;

public interface IPedidoServicePort {

    void savePedido(Pedido pedido);

    List<Pedido> getAllPedidos();

    Pedido getPedidoById(Long id);

    void updatePedido(Pedido pedido);

    void deletePedido(Long id);

}
