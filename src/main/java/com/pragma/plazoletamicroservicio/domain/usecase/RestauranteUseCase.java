package com.pragma.plazoletamicroservicio.domain.usecase;

import com.pragma.plazoletamicroservicio.domain.api.IRestauranteServicePort;
import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.domain.spi.IRestaurantePersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    public Page<Restaurante> getAllRestaurantes(Pageable pageable) {
        return restaurantePersistencePort.getAllRestaurantes(pageable);
    }


}
