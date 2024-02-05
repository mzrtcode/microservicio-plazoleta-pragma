package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.*;
import com.pragma.plazoletamicroservicio.application.exception.PedidoInvalidException;
import com.pragma.plazoletamicroservicio.application.mapper.IPedidoMapper;
import com.pragma.plazoletamicroservicio.application.mapper.IPlatoMapper;
import com.pragma.plazoletamicroservicio.domain.api.*;
import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import com.pragma.plazoletamicroservicio.domain.model.*;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.UsuarioDto;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.EmpleadoRestauranteEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PedidoEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.RestauranteEntity;
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
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
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
    @Mock
    private IPedidoMapper pedidoMapper;
    @Mock
    private IEmpleadoRestauranteServicePort empleadoRestauranteServicePort;
    @Mock
    private IPlatoMapper platoMapper;

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
        restaurante.setId(pedidoRequest.getRestauranteId());
        when(restauranteServicePort.getRestauranteById(pedidoRequest.getRestauranteId())).thenReturn(restaurante);

        // Plato Mock
        List<ListaPlatosPedido> listaPlatosPedido = new ArrayList<>();
        ListaPlatosPedido platoPedido = new ListaPlatosPedido();
        platoPedido.setIdPlato(1L);
        platoPedido.setCantidad(2);
        listaPlatosPedido.add(platoPedido);

        pedidoRequest.setListaPlatosPedido(listaPlatosPedido);

        Plato plato = new Plato();
        Restaurante platoRestaurante = new Restaurante();
        platoRestaurante.setId(pedidoRequest.getRestauranteId());
        plato.setRestaurante(platoRestaurante);

        when(platoServicePort.obtenerPlatoPorId(platoPedido.getIdPlato())).thenReturn(Optional.of(plato));

        // Pedido Mock
        Pedido pedido = new Pedido();
        pedido.setId(2L);
        PedidoEntity pedidoEntity = new PedidoEntity();

        when(platoServicePort.platoExistsById(1L)).thenReturn(true);
        when(autenticacionService.obtenerUsuarioSesionActual()).thenReturn(new UsuarioAutenticado());
        when(pedidoServicePort.savePedido(any())).thenReturn(pedidoEntity);

        // Act
        pedidoHandler.crearPedidoInDB(pedidoRequest);

        // PedidoPlato Mock
        PedidoPlato pedidoPlato = new PedidoPlato();

        // Assert
        verify(pedidoServicePort, times(1)).savePedido(any());
    }


    @Test
    public void testListarPedidosPorRestauranteEmpleado() throws PedidoInvalidException {
        // Setup data
        EstadoPedido estadoPedido = EstadoPedido.EN_PREPARACION;
        int pageNo = 0;
        int pageSize = 2;

        // Mock for the current session user
        UsuarioAutenticado usuarioSesion = new UsuarioAutenticado();
        usuarioSesion.setId(1L);
        when(autenticacionService.obtenerUsuarioSesionActual()).thenReturn(usuarioSesion);

        // Mock for the restaurant associated with the employee
        RestauranteEntity restauranteEntity = new RestauranteEntity();
        restauranteEntity.setId(1L);
        EmpleadoRestauranteEntity empleadoRestauranteEntity = new EmpleadoRestauranteEntity();
        empleadoRestauranteEntity.setRestaurante(restauranteEntity);
        when(empleadoRestauranteServicePort.findRestauranteByIdEmpleado(usuarioSesion.getId()))
                .thenReturn(Optional.of(empleadoRestauranteEntity));

        // Mock for the list of orders
        List<Pedido> listaPedidos = Arrays.asList(new Pedido(), new Pedido());
        Page<Pedido> pagePedidos = new PageImpl<>(listaPedidos);
        when(pedidoServicePort.listarPedidosPorRestauranteEmpleado(restauranteEntity.getId(), estadoPedido, PageRequest.of(pageNo, pageSize)))
                .thenReturn(pagePedidos);

        // Mock for the list of dishes per order
        when(pedidoPlatoServicePort.findByPedidoEntityId(any())).thenReturn(Arrays.asList(new PedidoPlato(), new PedidoPlato()));

        // Mock for the mapping of orders
        when(pedidoMapper.toPedidoDtoList(anyList())).thenReturn(Arrays.asList(new PedidoDto(), new PedidoDto()));

        // Mock for the mapping of dishes
        when(platoMapper.toPlatoDto(any())).thenReturn(new PlatoDTO());

        // Execute the method to be tested
        PedidoResponse pedidoResponse = pedidoHandler.listarPedidosPorRestauranteEmpleado(estadoPedido, pageNo, pageSize);

        // Verify results
        assertNotNull(pedidoResponse);
        assertEquals(pageNo, pedidoResponse.getPageNo());
        assertEquals(pageSize, pedidoResponse.getPageSize());
        assertEquals(listaPedidos.size(), pedidoResponse.getContent().size());
    }
    @Test
    public void testActualizarPedidoEmpleadoEnPreparacion() throws PedidoInvalidException {
        // Configurar datos de prueba
        Long idPedido = 1L;
        ActualizarPedidoRequest actualizarPedidoRequest = new ActualizarPedidoRequest();
        actualizarPedidoRequest.setEstadoPedido(EstadoPedido.EN_PREPARACION);

        UsuarioAutenticado usuarioSesion = new UsuarioAutenticado();
        usuarioSesion.setId(2L);  // Id de un usuario con rol de EMPLEADO
        usuarioSesion.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority(Rol.EMPLEADO.name())));
        when(autenticacionService.obtenerUsuarioSesionActual()).thenReturn(usuarioSesion);

        Pedido pedido = new Pedido();
        pedido.setId(idPedido);
        pedido.setEstadoPedido(EstadoPedido.PENDIENTE);
        when(pedidoServicePort.obtenerPedidoPorId(idPedido)).thenReturn(Optional.of(pedido));

        // Ejecutar el método a probar
        pedidoHandler.actualizarPedido(idPedido, actualizarPedidoRequest);

        // Verificar resultados
        assertEquals(EstadoPedido.EN_PREPARACION, pedido.getEstadoPedido());
        assertEquals(usuarioSesion.getId(), pedido.getIdEmpleadoAsignado());
        verify(pedidoServicePort, times(1)).savePedido(pedido);
    }

    @Test
    public void testActualizarPedidoEmpleadoListo() throws PedidoInvalidException {
        // Configurar datos de prueba
        Long idPedido = 1L;
        ActualizarPedidoRequest actualizarPedidoRequest = new ActualizarPedidoRequest();
        actualizarPedidoRequest.setEstadoPedido(EstadoPedido.LISTO);

        UsuarioAutenticado usuarioSesion = new UsuarioAutenticado();
        usuarioSesion.setId(2L);  // Id de un usuario con rol de EMPLEADO
        usuarioSesion.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority(Rol.EMPLEADO.name())));
        when(autenticacionService.obtenerUsuarioSesionActual()).thenReturn(usuarioSesion);

        Pedido pedido = new Pedido();
        pedido.setId(idPedido);
        pedido.setEstadoPedido(EstadoPedido.EN_PREPARACION);
        pedido.setIdCliente(123L);  // ID de un cliente válido
        when(pedidoServicePort.obtenerPedidoPorId(idPedido)).thenReturn(Optional.of(pedido));

        // Mock para el cliente
        UsuarioDto cliente = new UsuarioDto();
        cliente.setCelular("123456789");  // Número de celular válido
        when(usuarioServicePort.getUsuarioPorId(pedido.getIdCliente())).thenReturn(cliente);

        // Ejecutar el método a probar
        pedidoHandler.actualizarPedido(idPedido, actualizarPedidoRequest);

        // Verificar resultados
        assertEquals(EstadoPedido.LISTO, pedido.getEstadoPedido());
        verify(pedidoServicePort, times(1)).savePedido(pedido);
        verify(usuarioServicePort, times(1)).getUsuarioPorId(pedido.getIdCliente());
        verify(pedidoServicePort, times(1)).notificarUsuario(eq(cliente.getCelular()), anyString());
    }

    @Test
    public void testActualizarPedidoEmpleadoEntregado() throws PedidoInvalidException {
        // Configurar datos de prueba
        Long idPedido = 1L;
        String codigoRetiro = "39483932";
        ActualizarPedidoRequest actualizarPedidoRequest = new ActualizarPedidoRequest();
        actualizarPedidoRequest.setEstadoPedido(EstadoPedido.ENTREGADO);
        actualizarPedidoRequest.setCodigoRetiro(codigoRetiro);

        UsuarioAutenticado usuarioSesion = new UsuarioAutenticado();
        usuarioSesion.setId(2L);  // Id de un usuario con rol de EMPLEADO
        usuarioSesion.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority(Rol.EMPLEADO.name())));
        when(autenticacionService.obtenerUsuarioSesionActual()).thenReturn(usuarioSesion);

        Pedido pedido = new Pedido();
        pedido.setId(idPedido);
        pedido.setEstadoPedido(EstadoPedido.LISTO);
        pedido.setIdCliente(123L);  // ID de un cliente válido
        pedido.setCodigoRetiro(codigoRetiro);
        when(pedidoServicePort.obtenerPedidoPorId(idPedido)).thenReturn(Optional.of(pedido));

        // Mock para el cliente
        UsuarioDto cliente = new UsuarioDto();
        cliente.setCelular("123456789");  // Número de celular válido

        // Ejecutar el método a probar
        pedidoHandler.actualizarPedido(idPedido, actualizarPedidoRequest);

        // Verificar resultados
        assertEquals(EstadoPedido.ENTREGADO, pedido.getEstadoPedido());
        verify(pedidoServicePort, times(1)).savePedido(pedido);
    }

    @Test
    public void testActualizarPedidoClienteoCancelado() throws PedidoInvalidException {
        // Configurar datos de prueba
        Long idPedido = 1L;
        String codigoRetiro = "39483932";
        ActualizarPedidoRequest actualizarPedidoRequest = new ActualizarPedidoRequest();
        actualizarPedidoRequest.setEstadoPedido(EstadoPedido.CANCELADO);
        actualizarPedidoRequest.setCodigoRetiro(codigoRetiro);

        UsuarioAutenticado usuarioSesion = new UsuarioAutenticado();
        usuarioSesion.setId(2L);  // Id de un usuario con rol de EMPLEADO
        usuarioSesion.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority(Rol.CLIENTE.name())));
        when(autenticacionService.obtenerUsuarioSesionActual()).thenReturn(usuarioSesion);

        Pedido pedido = new Pedido();
        pedido.setId(idPedido);
        pedido.setEstadoPedido(EstadoPedido.PENDIENTE);
        pedido.setIdCliente(123L);  // ID de un cliente válido
        pedido.setCodigoRetiro(codigoRetiro);
        when(pedidoServicePort.obtenerPedidoPorId(idPedido)).thenReturn(Optional.of(pedido));

        // Mock para el cliente
        UsuarioDto cliente = new UsuarioDto();
        cliente.setCelular("123456789");  // Número de celular válido

        // Ejecutar el método a probar
        pedidoHandler.actualizarPedido(idPedido, actualizarPedidoRequest);

        // Verificar resultados
        assertEquals(EstadoPedido.CANCELADO, pedido.getEstadoPedido());
        verify(pedidoServicePort, times(1)).savePedido(pedido);
    }

    @Test
    public void testActualizarPedidoClienteoCanceladoTrhowsPedidoInvalidException() throws PedidoInvalidException {
        // Configurar datos de prueba
        Long idPedido = 1L;
        String codigoRetiro = "39483932";
        ActualizarPedidoRequest actualizarPedidoRequest = new ActualizarPedidoRequest();
        actualizarPedidoRequest.setEstadoPedido(EstadoPedido.CANCELADO);
        actualizarPedidoRequest.setCodigoRetiro(codigoRetiro);

        UsuarioAutenticado usuarioSesion = new UsuarioAutenticado();
        usuarioSesion.setId(2L);  // Id de un usuario con rol de EMPLEADO
        usuarioSesion.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority(Rol.CLIENTE.name())));
        when(autenticacionService.obtenerUsuarioSesionActual()).thenReturn(usuarioSesion);

        Pedido pedido = new Pedido();
        pedido.setId(idPedido);
        pedido.setEstadoPedido(EstadoPedido.LISTO);
        pedido.setIdCliente(123L);  // ID de un cliente válido
        pedido.setCodigoRetiro(codigoRetiro);
        when(pedidoServicePort.obtenerPedidoPorId(idPedido)).thenReturn(Optional.of(pedido));

        // Mock para el cliente
        UsuarioDto cliente = new UsuarioDto();
        cliente.setCelular("123456789");  // Número de celular válido

        // ACT ASSERT
        assertThrows(PedidoInvalidException.class, () -> {
            pedidoHandler.actualizarPedido(idPedido, actualizarPedidoRequest);
        });
    }

}