package com.pragma.plazoletamicroservicio.domain.usecase;

import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import com.pragma.plazoletamicroservicio.domain.model.Plato;
import com.pragma.plazoletamicroservicio.domain.spi.IPlatoPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlatoUseCaseTest {

    @Mock
    private  IPlatoPersistencePort platoPersistencePort;

    @InjectMocks
    private PlatoUseCase platoUseCase;
    @Test
    void savePlato() {
        Plato plato = new Plato();

        //ACTO
        platoUseCase.savePlato(plato);

        //ASSERT
        verify(platoPersistencePort).savePlato(plato);

    }

    @Test
    void obtenerPlatoPorIdExiste() throws PlatoNoExiste {
        Long idPlato = 1L;
        Plato plato = new Plato();
        plato.setId(idPlato);
        Optional platoOptional = Optional.of(plato);

        when(platoPersistencePort.obtenerPlatoPorId(idPlato)).thenReturn(platoOptional);

        // ACT
        Optional<Plato> resultado = platoUseCase.obtenerPlatoPorId(idPlato);

        // ASSERT
        assertTrue(resultado.isPresent());
        assertEquals(plato, resultado.get());
        verify(platoPersistencePort, times(1)).obtenerPlatoPorId(idPlato);
    }


    @Test
    void obtenerPlatoPorIdNoExiste() throws PlatoNoExiste {
        Long idPlato = 1L;
        Plato plato = new Plato();
        plato.setId(idPlato);
        Optional platoOptional = Optional.of(plato);

        when(platoPersistencePort.obtenerPlatoPorId(idPlato)).thenReturn(Optional.empty());

        // ACT
        Optional<Plato> resultado = platoUseCase.obtenerPlatoPorId(idPlato);

        // ASSERT
        assertFalse(resultado.isPresent());
        verify(platoPersistencePort, times(1)).obtenerPlatoPorId(idPlato);
    }

    @Test
    void actualizarPlatoExiste() throws PlatoNoExiste {
        // Preparación
        Plato plato = new Plato();
        plato.setPrecio(10.0);
        plato.setDescription("Descripción del plato");
        Long id = 1L;

        Plato platoPersistido = new Plato();
        platoPersistido.setPrecio(15.0);
        platoPersistido.setDescription("Descripción persistida");

        when(platoPersistencePort.obtenerPlatoPorId(id)).thenReturn(Optional.of(platoPersistido));
        doNothing().when(platoPersistencePort).savePlato(any(Plato.class));

        // ACT
        platoUseCase.actualizarPlato(plato, id);

        // Verificación
        assertEquals(plato.getPrecio(), platoPersistido.getPrecio());
        assertEquals(plato.getDescription(), platoPersistido.getDescription());
    }

    @Test
    public void testActualizarPlatoNoExistente() throws PlatoNoExiste {

        Long platoId = 1L;
        Plato plato = new Plato();

        when(platoPersistencePort.obtenerPlatoPorId(platoId)).thenReturn(Optional.empty());


        // ASSERT ACT
        assertThrows(PlatoNoExiste.class, () -> {
            platoUseCase.actualizarPlato(plato, platoId);
        });

    }


    @Test
    public void testGetPlatosByRestauranteId() {
        // Arrange
        Long id = 1L;
        Pageable pageable = PageRequest.of(0, 10);
        Plato plato = new Plato();
        List<Plato> platoList = Collections.singletonList(plato);
        Page<Plato> pagePlatos = new PageImpl<>(platoList);

        // Act
        when(platoPersistencePort.getPlatosByRestauranteId(eq(id), any(Pageable.class))).thenReturn(pagePlatos);
        Page<Plato> result = platoUseCase.getPlatosByRestauranteId(id, pageable);

        // Verify
        verify(platoPersistencePort).getPlatosByRestauranteId(eq(id), any(Pageable.class));

        // Assert
        assertEquals(pagePlatos, result);
    }
}