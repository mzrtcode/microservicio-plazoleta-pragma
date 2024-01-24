package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.adatper;

import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.domain.spi.IRestaurantePersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.RestauranteEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper.IRestauranteEntityMapper;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository.IRestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RestauranteJpaAdapter implements IRestaurantePersistencePort {

    private final IRestauranteRepository restauranteRepository;
    private final IRestauranteEntityMapper restauranteMapper;

    @Override
    public void saveRestaurante(Restaurante restaurante) {
        RestauranteEntity restauranteRequest = restauranteMapper.toEntity(restaurante);
        restauranteRepository.save(restauranteRequest);
    }
}
