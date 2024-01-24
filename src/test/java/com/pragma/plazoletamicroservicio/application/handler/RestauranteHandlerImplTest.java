package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.RestauranteRequest;
import com.pragma.plazoletamicroservicio.application.exception.RestauranteInvalidException;
import com.pragma.plazoletamicroservicio.application.mapper.IRestauranteMapper;
import com.pragma.plazoletamicroservicio.domain.api.IRestauranteServicePort;
import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.domain.model.Rol;
import com.pragma.plazoletamicroservicio.domain.spi.IUsuarioPersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.UsuarioDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestauranteHandlerImplTest {

    @Mock
    private  IRestauranteServicePort restauranteServicePort;
    @Mock
    private  IUsuarioPersistencePort usuarioServicePort;
    @Mock
    private  IRestauranteMapper restauranteMapper;
    @InjectMocks
    private RestauranteHandlerImpl restauranteHandler;



    @Test
    void saveRestaurantInDBUsuarioExisteRolNoValido() throws RestauranteInvalidException {

        RestauranteRequest restauranteRequest = new RestauranteRequest();
        restauranteRequest.setIdPropietario(2L);
        UsuarioDto usuario = new UsuarioDto();
        usuario.setId(2L);
        usuario.setRol(Rol.ADMINISTRADOR);

        when(usuarioServicePort.getUsuarioPorId(restauranteRequest.getIdPropietario())).thenReturn(usuario);
        assertThrows(RestauranteInvalidException.class, () -> {
            // Código que estás probando
            restauranteHandler.saveRestaurantInDB(restauranteRequest);
        });
    }


    @Test
    void saveRestaurantInDBUsuarioExisteRolValido() throws RestauranteInvalidException {

        Long idPropietario = 3L;
        RestauranteRequest restauranteRequest = new RestauranteRequest();
        restauranteRequest.setIdPropietario(idPropietario);
        restauranteRequest.setNit("123456-7");
        restauranteRequest.setTelefono("312144551");
        restauranteRequest.setNombre("Restaurante");
        UsuarioDto usuario = new UsuarioDto();
        Restaurante restaurante = new Restaurante();
        usuario.setId(2L);
        usuario.setRol(Rol.PROPIETARIO);


        //Validar que el propietario existe
        when(usuarioServicePort.getUsuarioPorId(restauranteRequest.getIdPropietario())).thenReturn(usuario);
        when(restauranteMapper.toRestaurante(restauranteRequest)).thenReturn(restaurante);

        //ACTO
        restauranteHandler.saveRestaurantInDB(restauranteRequest);

        // Assert
        verify(restauranteServicePort).saveRestaurante(restaurante);

    }

}
