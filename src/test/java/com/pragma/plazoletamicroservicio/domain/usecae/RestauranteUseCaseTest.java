package com.pragma.plazoletamicroservicio.domain.usecae;

import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.domain.spi.IRestaurantePersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RestauranteUseCaseTest {

    @Mock
    private IRestaurantePersistencePort restaurantePersistencePort;
    @InjectMocks
    private  RestauranteUseCase restauranteUseCase;
    @Test
    void testSaveRestaurante() {
        Restaurante restaurante = new Restaurante();

        //ACTO
        restauranteUseCase.saveRestaurante(restaurante);

        //ASSERT
        verify(restaurantePersistencePort).saveRestaurante(restaurante);
    }

}