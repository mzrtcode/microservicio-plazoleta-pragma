package com.pragma.plazoletamicroservicio.application.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteRequest {

    private Long id;

    private String nombre;

    private String direccion;

    private Long idPropietario;

    private String telefono;

    private String urlLogo;

    private String nit;
}
