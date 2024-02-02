package com.pragma.plazoletamicroservicio.application.dto;

import com.pragma.plazoletamicroservicio.domain.model.EstadoPedido;
import com.pragma.plazoletamicroservicio.domain.model.Plato;
import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDto {
    private Long id;

    private Long idCliente;

    private LocalDateTime fecha;

    private EstadoPedido estadoPedido;

    private Long idChef;

    private List<PlatoDTO> platos;

}
