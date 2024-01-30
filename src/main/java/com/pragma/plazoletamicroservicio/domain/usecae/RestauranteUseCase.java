package com.pragma.plazoletamicroservicio.domain.usecae;

import com.pragma.plazoletamicroservicio.domain.api.IRestauranteServicePort;
import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.domain.spi.IRestaurantePersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RestauranteUseCase implements IRestauranteServicePort {


    private final IRestaurantePersistencePort restaurantePersistencePort;
    @Override
    public void saveRestaurante(Restaurante restaurante) {
        restaurantePersistencePort.saveRestaurante(restaurante);
    }

    @Override
    public Restaurante getRestauranteById(Long id) throws RestauranteNotFoundException {
        return restaurantePersistencePort.getRestauranteById(id);
    }

    @Override
    public List<Restaurante> getAllRestaurantes() {
        return restaurantePersistencePort.getAllRestaurantes();
    }


}
