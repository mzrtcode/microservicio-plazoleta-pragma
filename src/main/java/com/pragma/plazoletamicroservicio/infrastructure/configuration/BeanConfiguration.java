package com.pragma.plazoletamicroservicio.infrastructure.configuration;

import com.pragma.plazoletamicroservicio.domain.api.IRestauranteServicePort;
import com.pragma.plazoletamicroservicio.domain.spi.IRestaurantePersistencePort;
import com.pragma.plazoletamicroservicio.domain.usecae.RestauranteUseCase;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.adatper.RestauranteJpaAdapter;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper.IRestauranteEntityMapper;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository.IRestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRestauranteRepository restauranteRepository;
    private final IRestauranteEntityMapper restauranteEntityMapper;


    @Bean
    public IRestaurantePersistencePort usuarioPersistencePort(){
        return new RestauranteJpaAdapter(restauranteRepository, restauranteEntityMapper);
    }

    @Bean
    public IRestauranteServicePort restauranteServicePort(){
        return new RestauranteUseCase(usuarioPersistencePort());
    }



}
