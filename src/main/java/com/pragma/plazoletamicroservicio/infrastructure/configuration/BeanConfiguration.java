package com.pragma.plazoletamicroservicio.infrastructure.configuration;

import com.pragma.plazoletamicroservicio.domain.api.ICategoriaServicePort;
import com.pragma.plazoletamicroservicio.domain.api.IPlatoServicePort;
import com.pragma.plazoletamicroservicio.domain.api.IRestauranteServicePort;
import com.pragma.plazoletamicroservicio.domain.api.IUsuarioServicePort;
import com.pragma.plazoletamicroservicio.domain.spi.ICategoriaPersistencePort;
import com.pragma.plazoletamicroservicio.domain.spi.IPlatoPersistencePort;
import com.pragma.plazoletamicroservicio.domain.spi.IRestaurantePersistencePort;
import com.pragma.plazoletamicroservicio.domain.spi.IUsuarioPersistencePort;
import com.pragma.plazoletamicroservicio.domain.usecase.CategoriaUseCase;
import com.pragma.plazoletamicroservicio.domain.usecase.PlatoUseCase;
import com.pragma.plazoletamicroservicio.domain.usecase.RestauranteUseCase;
import com.pragma.plazoletamicroservicio.domain.usecase.UsuarioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {




    private final IRestaurantePersistencePort restauranteServicePort;
    private final IPlatoPersistencePort platoServicePort;
    private final ICategoriaPersistencePort categoriaPersistencePort;
    private final IUsuarioPersistencePort usuarioServicePort;


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

    @Bean
    public IUsuarioServicePort usuarioServicePort(){
        return new UsuarioUseCase(usuarioServicePort);
    }


}
