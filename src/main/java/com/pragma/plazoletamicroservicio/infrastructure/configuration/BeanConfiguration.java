package com.pragma.plazoletamicroservicio.infrastructure.configuration;

import com.pragma.plazoletamicroservicio.domain.api.ICategoriaServicePort;
import com.pragma.plazoletamicroservicio.domain.api.IPlatoServicePort;
import com.pragma.plazoletamicroservicio.domain.api.IRestauranteServicePort;
import com.pragma.plazoletamicroservicio.domain.spi.ICategoriaPersistencePort;
import com.pragma.plazoletamicroservicio.domain.spi.IPlatoPersistencePort;
import com.pragma.plazoletamicroservicio.domain.spi.IRestaurantePersistencePort;
import com.pragma.plazoletamicroservicio.domain.usecae.CategoriaUseCase;
import com.pragma.plazoletamicroservicio.domain.usecae.PlatoUseCase;
import com.pragma.plazoletamicroservicio.domain.usecae.RestauranteUseCase;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.adatper.CategoriaJpaAdapter;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.adatper.RestauranteJpaAdapter;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper.ICategoriaEntityMapper;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper.IPlatoEntityMapper;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper.IRestauranteEntityMapper;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository.ICategoriaRepository;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository.IPlatoRepository;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository.IRestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {




    private final IRestaurantePersistencePort restauranteServicePort;
    private final IPlatoPersistencePort platoServicePort;
    private final ICategoriaPersistencePort categoriaPersistencePort;


    @Bean
    public IRestauranteServicePort restauranteServicePort() {
        return new RestauranteUseCase(restauranteServicePort);
    }




    @Bean
    public IPlatoServicePort platoServicePort() {
        return new PlatoUseCase(platoServicePort);
    }

    @Bean
    public ICategoriaServicePort categoriaServicePort(){
        return new CategoriaUseCase(categoriaPersistencePort);
    }




}
