package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository;

import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestauranteRepository extends JpaRepository<RestauranteEntity, Long> {

}
