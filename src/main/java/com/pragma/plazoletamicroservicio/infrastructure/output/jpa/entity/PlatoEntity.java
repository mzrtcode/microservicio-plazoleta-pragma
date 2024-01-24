package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PlatoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del plato es obligatorio")
    private String nombre;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_categoria")
    private CategoriaEntity categoria;

    @NotBlank(message = "Una descripcion es necesaria")
    private String description;

    @NotNull
    @Min(value = 0, message = "El precio debe ser mayor o igual a cero")
    private Double precio;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_restaurante")
    private RestauranteEntity restaurante;

    @NotBlank(message = "La URL de imagen es necesaria")
    private String urlImagen;

    private Boolean activo;

}
