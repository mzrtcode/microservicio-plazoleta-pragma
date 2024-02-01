package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository;

import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
}
