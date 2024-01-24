package com.pragma.plazoletamicroservicio.domain.spi;


import com.pragma.plazoletamicroservicio.domain.model.Restaurante;

import java.util.List;

public interface IRestaurantePersistencePort {

    void saveRestaurante(Restaurante restaurante);

}
