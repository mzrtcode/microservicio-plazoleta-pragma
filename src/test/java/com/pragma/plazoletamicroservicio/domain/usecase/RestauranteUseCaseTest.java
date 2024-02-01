package com.pragma.plazoletamicroservicio.domain.usecase;

import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.domain.spi.IRestaurantePersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


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

    @Test
    void getRestauranteById() throws RestauranteNotFoundException {
        Long idRestaurante =1L;

        //ACTO
        restauranteUseCase.getRestauranteById(idRestaurante);

        //ASSERT
        verify(restaurantePersistencePort).getRestauranteById(idRestaurante);
    }

    @Test
    public void testGetAllRestaurantes() {
        // Datos de prueba
        Pageable pageable = PageRequest.of(0, 10); // Puedes ajustar los valores según tus necesidades

        // Llamar al método que queremos probar
        Page<Restaurante> result = restauranteUseCase.getAllRestaurantes(pageable);

        verify(restaurantePersistencePort, times(1)).getAllRestaurantes(pageable);
    }
}