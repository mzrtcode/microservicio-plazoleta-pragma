package com.pragma.plazoletamicroservicio.domain.spi;


import com.pragma.plazoletamicroservicio.domain.model.Restaurante;

import java.util.List;

public interface IRestaurantePersistencePort {

    void saveRestaurante(Restaurante restaurante);

    List<Restaurante> getAllRestaurantes();

    Restaurante getRestauranteById(Long id);

    void updateRestaurante(Restaurante restaurante);

    void deleteRestaurante(Long id);
}
