package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository;

import com.pragma.plazoletamicroservicio.domain.model.Plato;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PlatoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPlatoRepository extends JpaRepository<PlatoEntity, Long> {

    Page<PlatoEntity> findByRestauranteId(Long id, Pageable pageable);

}
