package com.pragma.plazoletamicroservicio.domain.api;

import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.EmpleadoRestauranteEntity;

import java.util.Optional;

public interface IEmpleadoRestauranteServicePort {

    Optional<EmpleadoRestauranteEntity> findRestauranteByIdEmpleado(Long idEmpleado);
}
