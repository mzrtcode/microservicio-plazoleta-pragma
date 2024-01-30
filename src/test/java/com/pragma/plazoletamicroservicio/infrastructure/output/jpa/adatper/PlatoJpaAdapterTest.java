package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.adatper;

import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import com.pragma.plazoletamicroservicio.domain.model.Plato;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PlatoEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper.IPlatoEntityMapper;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository.IPlatoRepository;
import org.hibernate.validator.constraints.ru.INN;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.parameters.P;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    void getPlatosByRestauranteIdTest() {
        Long id = 1L;
        PlatoEntity platoEntity = new PlatoEntity();
        Plato plato = new Plato();
        List<PlatoEntity> platoEntityList = Arrays.asList(platoEntity);
        List<Plato> platoList = Arrays.asList(plato);

        when(platoRepository.findByRestauranteId(id)).thenReturn(platoEntityList);
        when(platoEntityMapper.toPlatoList(platoEntityList)).thenReturn(platoList);

        List<Plato> result = platoJpaAdapter.getPlatosByRestauranteId(id);

        verify(platoRepository, times(1)).findByRestauranteId(id);
        verify(platoEntityMapper, times(1)).toPlatoList(platoEntityList);
        assertEquals(platoList, result);
    }
}
