package com.pragma.plazoletamicroservicio.application.dto;



import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequest {

    @NotNull
    private Long idChef;

    @NotNull
    private Long restauranteId;

    @NotNull
    private List<ListaPlatosPedido> listaPlatosPedido;

}


