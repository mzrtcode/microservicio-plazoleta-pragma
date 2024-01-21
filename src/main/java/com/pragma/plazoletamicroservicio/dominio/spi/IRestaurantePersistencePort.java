package com.pragma.plazoletamicroservicio.dominio.spi;


import com.pragma.plazoletamicroservicio.dominio.model.Restaurante;

import java.util.List;

public interface IRestaurantePersistencePort {

    void saveRestaurante(Restaurante restaurante);

    List<Restaurante> getAllRestaurantes();

    Restaurante getRestauranteById(Long id);

    void updateRestaurante(Restaurante restaurante);

    void deleteRestaurante(Long id);
}
