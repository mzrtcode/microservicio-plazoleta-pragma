package com.pragma.plazoletamicroservicio.application.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteRequest {

    @NotBlank(message = "EL nombre es requerido")
    private String nombre;

    @NotBlank(message = "La direccion es obligatoria")
    private String direccion;

    @NotNull
    private Long idPropietario;

    @NotBlank
    @Pattern(regexp="^\\+?\\d{1,13}$")
    private String telefono;

    @NotBlank
    private String urlLogo;

    @Pattern(regexp = "\\d+")
    private String nit;
}
