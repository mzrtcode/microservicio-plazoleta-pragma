package com.pragma.plazoletamicroservicio.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaPlatosPedido {

    @NotNull
    private Long idPlato;

    @NotNull
    private int cantidad;
}
