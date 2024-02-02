package com.pragma.plazoletamicroservicio.domain.spi;

import com.pragma.plazoletamicroservicio.domain.model.EmpleadoRestaurante;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.EmpleadoRestauranteEntity;

import java.util.Optional;

public interface IEmpleadoRestaurantePersistencePort {

    EmpleadoRestauranteEntity registrarEmpleadoRestaurante(EmpleadoRestaurante empleadoRestaurante);

    Optional<EmpleadoRestauranteEntity> findEmpleadoRestauranteByEmpleadoId(Long idEmpleado);
}
