package com.pragma.plazoletamicroservicio.domain.spi;


import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IRestaurantePersistencePort {

    void saveRestaurante(Restaurante restaurante);

    Restaurante getRestauranteById(Long idRestaurante) throws RestauranteNotFoundException;

}
