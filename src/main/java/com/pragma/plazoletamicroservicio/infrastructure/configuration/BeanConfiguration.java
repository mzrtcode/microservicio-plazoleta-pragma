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
import com.pragma.plazoletamicroservicio.infrastructure.security.jwt.dto.UsuarioAutenticado;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
