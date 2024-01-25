package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.PlatoRequest;
import com.pragma.plazoletamicroservicio.application.mapper.IPlatoMapper;
import com.pragma.plazoletamicroservicio.domain.api.IPlatoServicePort;
import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import com.pragma.plazoletamicroservicio.domain.model.Plato;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlatoHandlerImplTest {

    @Mock
    private  IPlatoServicePort platoServicePort;

    @Mock
    private  IPlatoMapper platoMapper;

    @InjectMocks
    private  PlatoHandlerImpl platoHandler;

    @Test
    void savePlatoInDB() {
        Plato plato = new Plato();
        PlatoRequest platoRequest = new PlatoRequest();

        when(platoMapper.toPlato(platoRequest)).thenReturn(plato);

        // ACT
        platoHandler.savePlatoInDB(platoRequest);

        // ASSERT
        verify(platoServicePort, times(1)).savePlato(plato);

    }

    @Test
    void actualizarPlatoInDBPlatoNoExiste() throws PlatoNoExiste {
        Long idPlatoActualizar = 40L;
        PlatoRequest platoRequest = new PlatoRequest();
        when(platoServicePort.obtenerPlatoPorId(idPlatoActualizar)).thenReturn(Optional.empty());

        // ASSERT ACT
        assertThrows(PlatoNoExiste.class, () ->{
            platoHandler.savePlatoInDB(platoRequest);
        });

    }

    @Test
    void actualizarPlatoInDBPlatoExiste() throws PlatoNoExiste {
        PlatoRequest plato = new PlatoRequest();
        plato.setPrecio(10.0);
        plato.setDescription("Descripción del plato");
        Long id = 1L;

        Plato platoPersistido = new Plato();
        platoPersistido.setPrecio(15.0);
        platoPersistido.setDescription("Descripción persistida");

        when(platoServicePort.obtenerPlatoPorId(id)).thenReturn(Optional.of(platoPersistido));

        // ACT aqui se cambia los valores por referencia
        platoHandler.actualizarPlatoInDB(plato, id);

        // Verificación
        assertEquals(plato.getPrecio(), platoPersistido.getPrecio());
        assertEquals(plato.getDescription(), platoPersistido.getDescription());
    }
}