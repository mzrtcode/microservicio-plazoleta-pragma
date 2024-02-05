package com.pragma.plazoletamicroservicio.application.dto;

import com.pragma.plazoletamicroservicio.domain.model.EstadoPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActualizarPedidoRequest {

    private EstadoPedido estadoPedido;
    private String codigoRetiro;

}
