package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.EmpleadoRequest;
import com.pragma.plazoletamicroservicio.application.exception.UsuarioNoRegistradoException;
import com.pragma.plazoletamicroservicio.domain.api.IRestauranteServicePort;
import com.pragma.plazoletamicroservicio.domain.api.IUsuarioServicePort;
import com.pragma.plazoletamicroservicio.domain.model.EmpleadoRestaurante;
import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.domain.spi.IEmpleadoRestaurantePersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.UsuarioDto;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;
import com.pragma.plazoletamicroservicio.infrastructure.security.jwt.AutenticacionService;
import com.pragma.plazoletamicroservicio.infrastructure.security.jwt.dto.UsuarioAutenticado;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioHandlerImplTest {

    @Mock
    private IEmpleadoRestaurantePersistencePort empleadoRestaurantePersistencePort;

    @Mock
    private IRestauranteServicePort restauranteServicePort;

    @Mock
    private IUsuarioServicePort usuarioServicePort;

    @Mock
    private AutenticacionService autenticacionService;

    @InjectMocks
    private UsuarioHandlerImpl usuarioHandler;

    @Test
    void testCrearEmpleado() throws RestauranteNotFoundException, UsuarioNoRegistradoException {


        // Arrange
        EmpleadoRequest empleadoRequest = new EmpleadoRequest();
        empleadoRequest.setNombre("Manuel");
        empleadoRequest.setCelular("+5712124541");
        empleadoRequest.setCorreo("manuel@gmail.com");
        empleadoRequest.setClave("usuario123");
        empleadoRequest.setIdRestaurante(1L);




        Restaurante restaurante = new Restaurante();
        // Configurar el comportamiento esperado de los mocks
        when(restauranteServicePort.getRestauranteById(empleadoRequest.getIdRestaurante())).thenReturn(restaurante);
        when(autenticacionService.obtenerUsuarioSesionActual()).thenReturn(new UsuarioAutenticado());

        UsuarioDto empleadoCreadoMock = new UsuarioDto();
        when(usuarioServicePort.crearEmpleado(any())).thenReturn(empleadoCreadoMock);

        // Act
        UsuarioDto result = usuarioHandler.crearEmpleado(empleadoRequest);

        // Assert
        assertNotNull(result);
        verify(empleadoRestaurantePersistencePort, times(1)).registrarEmpleadoRestaurante(any());
    }

}