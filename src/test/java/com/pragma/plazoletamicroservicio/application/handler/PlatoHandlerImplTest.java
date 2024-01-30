package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.PlatoRequest;
import com.pragma.plazoletamicroservicio.application.dto.PlatoResponse;
import com.pragma.plazoletamicroservicio.application.mapper.IPlatoMapper;
import com.pragma.plazoletamicroservicio.domain.api.IPlatoServicePort;
import com.pragma.plazoletamicroservicio.domain.api.IRestauranteServicePort;
import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import com.pragma.plazoletamicroservicio.domain.model.Plato;
import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;
import com.pragma.plazoletamicroservicio.infrastructure.security.jwt.AutenticacionService;
import com.pragma.plazoletamicroservicio.infrastructure.security.jwt.dto.UsuarioAutenticado;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlatoHandlerImplTest {

    @Mock
    private  IPlatoServicePort platoServicePort;

    @Mock
    private  IPlatoMapper platoMapper;

    @Mock
    private  IRestauranteServicePort restauranteServicePort;

    @Mock
    private AutenticacionService autenticacionService;

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
            platoHandler.actualizarPlatoInDB(platoRequest, idPlatoActualizar);
        });

    }

    @Test
    void actualizarPlatoInDBPlatoExiste() throws PlatoNoExiste, RestauranteNotFoundException {
        PlatoRequest plato = new PlatoRequest();
        plato.setPrecio(10.0);
        plato.setDescription("Descripción del plato");
        Long id = 1L;

        Long restauranteId = 20L;


        Restaurante restaurante = new Restaurante();
        restaurante.setId(restauranteId);
        restaurante.setIdPropietario(2L);

        Plato platoPersistido = new Plato();
        platoPersistido.setPrecio(15.0);
        platoPersistido.setDescription("Descripción persistida");
        platoPersistido.setRestaurante(restaurante);
        platoPersistido.setActivo(false);


        UsuarioAutenticado usuarioAutenticado = new UsuarioAutenticado();
        usuarioAutenticado.setId(2L);

        when(platoServicePort.obtenerPlatoPorId(id)).thenReturn(Optional.of(platoPersistido));
        when(restauranteServicePort.getRestauranteById(restauranteId)).thenReturn(restaurante);
        when(autenticacionService.obtenerUsuarioSesionActual()).thenReturn(usuarioAutenticado);

        // ACT aqui se cambia los valores por referencia
        platoHandler.actualizarPlatoInDB(plato, id);

        // Verificación
        assertEquals(plato.getPrecio(), platoPersistido.getPrecio());
        assertEquals(plato.getDescription(), platoPersistido.getDescription());
    }

    @Test
    void testGetPlatosByRestauranteId() {
        // Datos de prueba
        Long restauranteId = 1L;
        List<Plato> platosMock = List.of(new Plato(/* Datos de prueba */));
        List<PlatoResponse> platoResponseMock = Collections.singletonList(new PlatoResponse(/* Datos de prueba */));

        // Configurar el comportamiento del mock de platoServicePort
        when(platoServicePort.getPlatosByRestauranteId(restauranteId)).thenReturn(platosMock);

        // Llamar al método que queremos probar
        List<PlatoResponse> result = platoHandler.getPlatosByRestauranteId(restauranteId);

        // Verificar que se llamó al menos una vez al método getPlatosByRestauranteId en platoServicePort
        verify(platoServicePort, times(1)).getPlatosByRestauranteId(restauranteId);

    }
}