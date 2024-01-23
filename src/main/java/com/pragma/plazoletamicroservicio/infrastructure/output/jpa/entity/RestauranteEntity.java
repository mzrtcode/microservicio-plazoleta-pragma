package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "restaurantes")
public class RestauranteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String direccion;

    private Long idPropietario;

    private String telefono;

    private String urlLogo;

    private String nit;

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    private List<PlatoEntity> platos;

}
