package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.ListaPlatosPedido;
import com.pragma.plazoletamicroservicio.application.dto.PedidoRequest;
import com.pragma.plazoletamicroservicio.application.exception.PedidoInvalidException;
import com.pragma.plazoletamicroservicio.domain.api.*;
import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import com.pragma.plazoletamicroservicio.domain.model.*;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.UsuarioDto;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PedidoEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;
import com.pragma.plazoletamicroservicio.infrastructure.security.jwt.AutenticacionService;
import com.pragma.plazoletamicroservicio.infrastructure.security.jwt.dto.UsuarioAutenticado;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoHandlerImplTest {

    @Mock
    private IUsuarioServicePort usuarioServicePort;
    @Mock

    private IPlatoServicePort platoServicePort;
    @Mock

    private IRestauranteServicePort restauranteServicePort;
    @Mock
    private IPedidoServicePort pedidoServicePort;
    @Mock
    private IPedidoPlatoServicePort pedidoPlatoServicePort;

    @Mock
    private AutenticacionService autenticacionService;

    @InjectMocks
    private PedidoHandlerImpl pedidoHandler;

    @Test
    void testCrearPedidoInDB() throws PlatoNoExiste, RestauranteNotFoundException, PedidoInvalidException {
        // Arrange
        PedidoRequest pedidoRequest = new PedidoRequest();
        pedidoRequest.setIdChef(20L);
        pedidoRequest.setRestauranteId(1L);

        // Usuario Mock
        UsuarioDto usuarioChef = new UsuarioDto();
        usuarioChef.setRol(Rol.EMPLEADO);
        when(usuarioServicePort.getUsuarioPorId(pedidoRequest.getIdChef())).thenReturn(usuarioChef);

        // Restaurante Mock
        Restaurante restaurante = new Restaurante();
        when(restauranteServicePort.getRestauranteById(pedidoRequest.getRestauranteId())).thenReturn(restaurante);

        // Plato Mock
        List<ListaPlatosPedido> listaPlatosPedido = new ArrayList<>();
        ListaPlatosPedido platoPedido = new ListaPlatosPedido();
        platoPedido.setIdPlato(1L);
        platoPedido.setCantidad(2);
        listaPlatosPedido.add(platoPedido);

        pedidoRequest.setListaPlatosPedido(listaPlatosPedido);

        // Pedido Mock
        Pedido pedido = new Pedido();
        pedido.setId(2L);
        PedidoEntity pedidoEntity = new PedidoEntity();

        when(platoServicePort.platoExistsById(1L)).thenReturn(true);
        when(autenticacionService.obtenerUsuarioSesionActual()).thenReturn(new UsuarioAutenticado());
        when(pedidoServicePort.savePedido(any())).thenReturn(pedidoEntity);

        // Simular un escenario en el que obtenerPlatoPorId devuelve un Optional vacío
        when(platoServicePort.obtenerPlatoPorId(platoPedido.getIdPlato())).thenReturn(Optional.of(new Plato()));


        // Act
        pedidoHandler.crearPedidoInDB(pedidoRequest);

        // PedidoPlato Mock
        PedidoPlato pedidoPlato = new PedidoPlato();

        // Assert
        verify(pedidoServicePort, times(1)).savePedido(any());
    }

    @Test
    void testCrearPedidoInDB_UsuarioNoEsEmpleado() {
        // Arrange
        PedidoRequest pedidoRequest = new PedidoRequest();
        pedidoRequest.setIdChef(20L);

        // Usuario Mock
        UsuarioDto usuarioChef = new UsuarioDto();
        usuarioChef.setRol(Rol.CLIENTE); // Cambia el rol a CLIENTE para simular el error
        when(usuarioServicePort.getUsuarioPorId(pedidoRequest.getIdChef())).thenReturn(usuarioChef);

        // Act and Assert
        assertThrows(PedidoInvalidException.class, () -> pedidoHandler.crearPedidoInDB(pedidoRequest));
    }




}