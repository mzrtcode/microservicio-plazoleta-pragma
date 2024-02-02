package com.pragma.plazoletamicroservicio.application.dto;

import com.pragma.plazoletamicroservicio.domain.model.Categoria;
import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlatoDTO {

    private Long id;
    private String nombre;

    private Categoria categoria;

    private String description;

    private Double precio;

    private String urlImagen;

    private Boolean activo;
}
