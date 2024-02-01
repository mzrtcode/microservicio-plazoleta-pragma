package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.PlatoDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
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
    public void testGetPlatosByRestauranteId() {
        // Arrange
        Long id = 1L;
        int pageNo = 0;
        int pageSize = 1;
        Plato plato = new Plato();
        PlatoDTO platoDTO = new PlatoDTO();
        List<Plato> platoList = Collections.singletonList(plato);
        List<PlatoDTO> platoDTOList = Collections.singletonList(platoDTO);
        Page<Plato> pagePlatos = new PageImpl<>(platoList);

        when(platoServicePort.getPlatosByRestauranteId(id, PageRequest.of(pageNo, pageSize)))
                .thenReturn(pagePlatos);
        when(platoMapper.toPlatoDtoList(platoList)).thenReturn(platoDTOList);

        // Act
        PlatoResponse platoResponse = platoHandler.getPlatosByRestauranteId(id, pageNo, pageSize);

        // Assert
        assertEquals(platoDTOList, platoResponse.getContent());
        assertEquals(pageNo, platoResponse.getPageNo());
        assertEquals(pageSize, platoResponse.getPageSize());
        assertEquals(pagePlatos.getTotalElements(), platoResponse.getTotalElements());
        assertEquals(pagePlatos.getTotalPages(), platoResponse.getTotalPages());
        assertEquals(pagePlatos.isLast(), platoResponse.isLast());
    }

    @Test
    public void testPlatoExistsById() {
        Long id = 1L;
        platoServicePort.platoExistsById(id);
        verify(platoServicePort, times(1)).platoExistsById(id);
    }
}
