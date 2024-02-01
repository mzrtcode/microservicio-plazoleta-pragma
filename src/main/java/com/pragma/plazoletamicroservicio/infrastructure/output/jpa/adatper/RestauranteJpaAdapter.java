package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.adatper;

import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.domain.spi.IRestaurantePersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.RestauranteEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper.IRestauranteEntityMapper;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository.IRestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Restaurante getRestauranteById(Long idRestaurante) throws RestauranteNotFoundException {
        Optional<RestauranteEntity> restauranteEntity = restauranteRepository.findById(idRestaurante);
        if(restauranteEntity.isEmpty()) throw new RestauranteNotFoundException("No se encontro el restaurante");
        return restauranteMapper.toRestaurante(restauranteEntity.get());
    }

    @Override
    public Page <Restaurante> getAllRestaurantes(Pageable pageable) {
        Page<RestauranteEntity> entities = restauranteRepository.findAll(pageable);
        return  entities.map(restauranteMapper::toRestaurante);

    }
}
