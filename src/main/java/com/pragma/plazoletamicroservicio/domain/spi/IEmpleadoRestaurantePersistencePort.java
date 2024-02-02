package com.pragma.plazoletamicroservicio.domain.spi;

import com.pragma.plazoletamicroservicio.domain.model.EmpleadoRestaurante;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.EmpleadoRestauranteEntity;

public interface IEmpleadoRestaurantePersistencePort {

    EmpleadoRestauranteEntity registrarEmpleadoRestaurante(EmpleadoRestaurante empleadoRestaurante);
}
