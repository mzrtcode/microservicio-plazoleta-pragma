package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity;


import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class PlatoEntity {

    private Long id;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoriaEntity categoria;

    private String description;

    private Double precio;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private RestauranteEntity restaurante;

    private String urlImagen;

    private Boolean activo;

}
