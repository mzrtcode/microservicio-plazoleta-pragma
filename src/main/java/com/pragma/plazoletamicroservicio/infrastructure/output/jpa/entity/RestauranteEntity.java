package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "restaurantes")
public class RestauranteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "EL nombre es requerida")
    private String nombre;

    @NotBlank(message = "La direccion es requerida")
    private String direccion;

    @Min(value = 1 , message = "El id del propietario es requerido")
    private Long idPropietario;

    @NotBlank(message = "El telefono es requerido")
    private String telefono;

    @NotBlank(message = "La url del logo es requerida")
    private String urlLogo;

    @NotBlank(message = "El NIT es requerida")
    private String nit;

}
