package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.adatper;

import com.pragma.plazoletamicroservicio.domain.model.EmpleadoRestaurante;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.EmpleadoRestauranteEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper.IEmpleadoRestauranteMapper;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository.IEmpleadoRestauranteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmpleadoRestauranteJpaAdapterTest {

    @Mock
    private IEmpleadoRestauranteRepository empleadoRestauranteRepository;

    @Mock
    private IEmpleadoRestauranteMapper empleadoRestauranteMapper;

    @InjectMocks
    private EmpleadoRestauranteJpaAdapter empleadoRestauranteJpaAdapter;

    @Test
    void registrarEmpleadoRestaurante() {
        //MOCKS
        EmpleadoRestaurante empleadoRestaurante = new EmpleadoRestaurante();
        EmpleadoRestauranteEntity empleadoRestauranteEntity = new EmpleadoRestauranteEntity();

        when(empleadoRestauranteMapper.tooEntity(empleadoRestaurante)).thenReturn(empleadoRestauranteEntity);
        when(empleadoRestauranteRepository.save(empleadoRestauranteEntity)).thenReturn(empleadoRestauranteEntity);

        // ACT
        EmpleadoRestauranteEntity resultado = empleadoRestauranteJpaAdapter.registrarEmpleadoRestaurante(empleadoRestaurante);

        // ASSERT
        assertEquals(empleadoRestauranteEntity, resultado);
    }

    @Test
    void findEmpleadoRestauranteByEmpleadoId() {
        Long idEmpleado = 30L;
        Optional<EmpleadoRestauranteEntity> optional = Optional.of(new EmpleadoRestauranteEntity());

        when(empleadoRestauranteRepository.findByEmpleadoId(idEmpleado)).thenReturn(optional);

        // ACT
        Optional<EmpleadoRestauranteEntity> resultado = empleadoRestauranteJpaAdapter.findEmpleadoRestauranteByEmpleadoId(idEmpleado);

        // ASSERT
        assertEquals(optional, resultado);
        verify(empleadoRestauranteRepository, times(1)).findByEmpleadoId(idEmpleado);
    }
}