package com.pragma.plazoletamicroservicio.domain.api;


import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;

import java.util.List;

public interface IRestauranteServicePort {

    void saveRestaurante(Restaurante restaurante);

    Restaurante getRestauranteById(Long id) throws RestauranteNotFoundException;

    List<Restaurante> getAllRestaurantes();

}
