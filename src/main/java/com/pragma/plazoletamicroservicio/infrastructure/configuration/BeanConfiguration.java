package com.pragma.plazoletamicroservicio.infrastructure.configuration;

import com.pragma.plazoletamicroservicio.domain.api.*;
import com.pragma.plazoletamicroservicio.domain.spi.*;
import com.pragma.plazoletamicroservicio.domain.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {




    private final IRestaurantePersistencePort restaurantePersistencePort;
    private final IPlatoPersistencePort platoServicePort;
    private final ICategoriaPersistencePort categoriaPersistencePort;
    private final IUsuarioPersistencePort usuarioServicePort;
    private final IPedidoPersistencePort pedidoPersistencePort;
    private final IPedidoPlatoPersistencePort platoPersistencePort;


    @Bean
    public IRestauranteServicePort restauranteServicePort() {
        return new RestauranteUseCase(restaurantePersistencePort);
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

    @Bean
    public IPedidoServicePort pedidoServicePort(){
        return new PedidoUseCase(pedidoPersistencePort);
    }

    @Bean
    public IPedidoPlatoServicePort pedidoPlatoServicePort(){
        return new PedidoPlatoUseCase(platoPersistencePort);
    }


}
