package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository;


import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.EmpleadoRestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmpleadoRestauranteRepository extends JpaRepository<EmpleadoRestauranteEntity, Long> {
}
