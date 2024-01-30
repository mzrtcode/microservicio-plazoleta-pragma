package com.pragma.plazoletamicroservicio.domain.usecae;

import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import com.pragma.plazoletamicroservicio.domain.model.Plato;
import com.pragma.plazoletamicroservicio.domain.spi.IPlatoPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}