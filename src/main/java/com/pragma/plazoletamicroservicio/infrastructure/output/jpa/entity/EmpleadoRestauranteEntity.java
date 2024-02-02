package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "empleado_restaurante")
public class EmpleadoRestauranteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long idEmpleado;

    @OneToOne
    @JoinColumn(name = "id_restaurante", referencedColumnName = "id")
    private RestauranteEntity restaurante;

}
