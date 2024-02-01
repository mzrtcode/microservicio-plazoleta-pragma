package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository;

import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PedidoPlatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPedidoPlatoRepository extends JpaRepository<PedidoPlatoEntity, Long> {
}
