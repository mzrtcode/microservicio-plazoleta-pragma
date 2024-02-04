package com.pragma.plazoletamicroservicio.domain.usecase;

import com.pragma.plazoletamicroservicio.domain.spi.IEmpleadoRestaurantePersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.EmpleadoRestauranteEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmpleadoRestauranteUseCaseTest {

    @Mock
    private IEmpleadoRestaurantePersistencePort empleadoRestaurantePersistencePort;

    @InjectMocks
    private EmpleadoRestauranteUseCase empleadoRestauranteUseCase;

    @Test
    void findRestauranteByIdEmpleado() {
        Long idEmpleado = 23L;
        Optional<EmpleadoRestauranteEntity> optional = Optional.empty();

        when(empleadoRestaurantePersistencePort.findEmpleadoRestauranteByEmpleadoId(idEmpleado)).thenReturn(optional);

        // ACT
        empleadoRestauranteUseCase.findRestauranteByIdEmpleado(idEmpleado);

        // ASSERT
        verify(empleadoRestaurantePersistencePort, times(1)).findEmpleadoRestauranteByEmpleadoId(idEmpleado);
    }
}