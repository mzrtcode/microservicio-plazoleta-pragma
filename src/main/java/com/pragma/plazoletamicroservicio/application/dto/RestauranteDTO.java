package com.pragma.plazoletamicroservicio.application.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteDTO {

    private Long id;

    private String nombre;

    private String urlLogo;

}
