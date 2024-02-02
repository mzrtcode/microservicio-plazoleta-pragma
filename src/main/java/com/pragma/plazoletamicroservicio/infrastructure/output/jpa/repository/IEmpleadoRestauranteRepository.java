package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository;


import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.EmpleadoRestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IEmpleadoRestauranteRepository extends JpaRepository<EmpleadoRestauranteEntity, Long> {
    @Query("SELECT e FROM EmpleadoRestauranteEntity e WHERE e.idEmpleado = :idEmpleado")
    Optional<EmpleadoRestauranteEntity> findByEmpleadoId(Long idEmpleado);
}
