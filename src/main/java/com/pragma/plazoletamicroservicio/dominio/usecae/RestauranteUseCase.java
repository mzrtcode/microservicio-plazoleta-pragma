package com.pragma.plazoletamicroservicio.dominio.usecae;

import com.pragma.plazoletamicroservicio.dominio.api.IRestauranteServicePort;
import com.pragma.plazoletamicroservicio.dominio.model.Restaurante;
import com.pragma.plazoletamicroservicio.dominio.spi.IRestaurantePersistencePort;
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
    public List<Restaurante> getAllRestaurantes() {
        return restaurantePersistencePort.getAllRestaurantes();
    }

    @Override
    public Restaurante getRestauranteById(Long id) {
        return restaurantePersistencePort.getRestauranteById(id);
    }

    @Override
    public void updateRestaurante(Restaurante restaurante) {
        restaurantePersistencePort.updateRestaurante(restaurante);
    }

    @Override
    public void deleteRestaurante(Long id) {
        restaurantePersistencePort.deleteRestaurante(id);
    }
}
