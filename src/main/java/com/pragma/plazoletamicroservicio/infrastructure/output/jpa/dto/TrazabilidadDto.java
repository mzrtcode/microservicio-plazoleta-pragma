package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto;

import com.pragma.plazoletamicroservicio.domain.model.EstadoPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrazabilidadDto {

    private Long idPedido;
    
    private Long idCliente;

    private Long idEmpleado;

    private EstadoPedido estadoPedido;

}
