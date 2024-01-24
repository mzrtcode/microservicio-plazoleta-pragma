package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository;

import com.pragma.plazoletamicroservicio.domain.model.Plato;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PlatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlatoRepository extends JpaRepository<PlatoEntity, Long> {

}
