package com.pragma.plazoletamicroservicio.domain.api;

import com.pragma.plazoletamicroservicio.domain.model.PedidoPlato;

import java.util.List;

public interface IPedidoPlatoServicePort {

    void savePedidoPlato(PedidoPlato pedidoPlato);

    List<PedidoPlato>  findByPedidoEntityId(Long idPedido);
}
