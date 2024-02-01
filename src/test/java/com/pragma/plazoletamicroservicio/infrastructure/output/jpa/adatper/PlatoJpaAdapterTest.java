package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.adatper;

import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import com.pragma.plazoletamicroservicio.domain.model.Plato;
import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PlatoEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.RestauranteEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper.IPlatoEntityMapper;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository.IPlatoRepository;
import org.hibernate.validator.constraints.ru.INN;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlatoJpaAdapterTest {

    @Mock
    private IPlatoRepository platoRepository;

    @Mock
    private IPlatoEntityMapper platoEntityMapper;

    @InjectMocks
    private PlatoJpaAdapter platoJpaAdapter;

    @Test
    void savePlato() {
        Plato plato = new Plato();
        PlatoEntity platoEntity = new PlatoEntity();

        when(platoEntityMapper.toEntity(plato)).thenReturn(platoEntity);

        // ACT
        platoJpaAdapter.savePlato(plato);

        // ASSERT
        verify(platoRepository, times(1)).save(platoEntity);


    }


    @Test
    void obtenerPlatoPorIdExiste() throws PlatoNoExiste {
        // Arrange
        Long platoId = 1L;
        PlatoEntity platoEntity = new PlatoEntity();
        Plato plato = new Plato();

        when(platoRepository.findById(platoId)).thenReturn(Optional.of(platoEntity));
        when(platoEntityMapper.toOptionalPlato(Optional.of(platoEntity))).thenReturn(Optional.of(plato));

        // Act
        Optional<Plato> resultado = platoJpaAdapter.obtenerPlatoPorId(platoId);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals(plato, resultado.get());
    }


    @Test
    void testGetPlatosByRestauranteId() {
        // Datos de prueba
        Long restauranteId = 1L;
        Pageable pageable = PageRequest.of(0, 5);

        List<PlatoEntity> platoEntities = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PlatoEntity entity = new PlatoEntity();
            entity.setNombre("Plato" + i);
            platoEntities.add(entity);
        }
        Page<PlatoEntity> entities = new PageImpl<>(platoEntities);

        // Configurar el comportamiento del mock de platoRepository
        when(platoRepository.findByRestauranteId(eq(restauranteId), eq(pageable)))
                .thenReturn(entities);

        // Configurar el comportamiento del mock de platoEntityMapper
        List<Plato> platoDTOMock = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Plato platoDTO = new Plato();
            platoDTO.setNombre("Plato" + i);
            platoDTOMock.add(platoDTO);
        }
        when(platoEntityMapper.toDto(any(PlatoEntity.class))).thenReturn(platoDTOMock.get(0));

        // Llamar al método que queremos probar
        Page<Plato> platos = platoJpaAdapter.getPlatosByRestauranteId(restauranteId, pageable);

        // Verificar que se llamó al menos una vez al método findByRestauranteId en platoRepository
        verify(platoRepository, times(1)).findByRestauranteId(eq(restauranteId), eq(pageable));

        // Verificar el resultado
        assertEquals(5, platos.getTotalElements());
        assertEquals(5, platos.getContent().size());
        // Añade más verificaciones según la estructura de tu respuesta

        // Puedes verificar otras propiedades de la respuesta, por ejemplo:
        assertEquals(pageable.getPageNumber(), platos.getNumber());
        assertEquals(pageable.getPageSize(), platos.getSize());
    }

    @Test
    void testPlatoExistsById() {
        // Arrange
        Long platoId = 1L;

        when(platoRepository.existsById(platoId)).thenReturn(true);

        // Act
        boolean result = platoJpaAdapter.platoExistsById(platoId);

        // Assert
        assertTrue(result);
        verify(platoRepository, times(1)).existsById(platoId);
    }
}
