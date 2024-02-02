package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.EmpleadoRestauranteEntity;

import java.util.Optional;

public interface IEmpleadoRestauranteHandler {
    Optional<EmpleadoRestauranteEntity> findRestauranteByIdEmpleado (Long idEmpleado);
}
