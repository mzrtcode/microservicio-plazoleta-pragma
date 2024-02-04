package com.pragma.plazoletamicroservicio.domain.usecase;

import com.pragma.plazoletamicroservicio.domain.model.EstadoPedido;
import com.pragma.plazoletamicroservicio.domain.model.Pedido;
import com.pragma.plazoletamicroservicio.domain.spi.IPedidoPersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PedidoEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoUseCaseTest {

    @Mock
    private IPedidoPersistencePort pedidoPersistencePort;

    @InjectMocks
    private PedidoUseCase pedidoUseCase;
    @Test
    void savePedido() {
        Pedido pedido = new Pedido();
        PedidoEntity pedidoEntity = new PedidoEntity();

        when(pedidoPersistencePort.savePedido(pedido)).thenReturn(pedidoEntity);

        // ACT
        PedidoEntity resultado = pedidoUseCase.savePedido(pedido);

        // ASSERT
        assertEquals(pedidoEntity, resultado);
        verify(pedidoPersistencePort, times(1)).savePedido(pedido);
    }

    @Test
    void existsByIdClienteAndEstadoPedidoIn() {
        Long idCliente = 20L;
        List<EstadoPedido> listaEstados = Arrays.asList(EstadoPedido.EN_PREPARACION, EstadoPedido.ENTREGADO);

        when(pedidoPersistencePort.existsByIdClienteAndEstadoPedidoIn(idCliente, listaEstados)).thenReturn(true);

        // ACT
        boolean resultado = pedidoUseCase.existsByIdClienteAndEstadoPedidoIn(idCliente, listaEstados);

        // ASSERT
        assertEquals(true, resultado);
    }

    @Test
    void listarPedidosPorRestauranteEmpleado() {
        // Datos de prueba
        int pageNo = 0;
        int pageSize = 10;
        Long idCliente = 39L;
        EstadoPedido estadoPedido = EstadoPedido.PENDIENTE;
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Pedido> page = new PageImpl<>(Collections.emptyList(), pageable, 0);

        when(pedidoPersistencePort.listarPedidosPorRestauranteEmpleado(idCliente, estadoPedido, pageable)).thenReturn(page);

        // ACT
        Page<Pedido> resultado = pedidoUseCase.listarPedidosPorRestauranteEmpleado(idCliente, estadoPedido, pageable);

        // ASSERT
        verify(pedidoPersistencePort, times(1)).listarPedidosPorRestauranteEmpleado(idCliente, estadoPedido, pageable);

        // Verifica el contenido de la página en lugar del objeto pageable
        assertEquals(page.getContent(), resultado.getContent());
        assertEquals(page.getNumber(), resultado.getNumber());
        assertEquals(page.getSize(), resultado.getSize());
        assertEquals(page.getTotalElements(), resultado.getTotalElements());
        assertEquals(page.getTotalPages(), resultado.getTotalPages());
        assertEquals(page.getSort(), resultado.getSort());
        // Puedes añadir más comparaciones según tus necesidades
    }

    @Test
    void obtenerPedidoPorId() {
        Long idPedido = 20L;
        Optional<Pedido> optional = Optional.empty();

        when(pedidoPersistencePort.obtenerPedidoPorId(idPedido)).thenReturn(optional);

        // ACT
        Optional<Pedido> resultado = pedidoUseCase.obtenerPedidoPorId(idPedido);

        // ASSERT
        assertEquals(optional, resultado);
        verify(pedidoPersistencePort, times(1)).obtenerPedidoPorId(idPedido);
    }

    @Test
    void notificarUsuario() {
        String destinatario = "+572121545";
        String mensaje = "Este es un mensaje de prueba";


        // ACT
        pedidoUseCase.notificarUsuario(mensaje, destinatario);

        //ASSERT
        verify(pedidoPersistencePort, times(1)).notificarUsuario(mensaje, destinatario);


    }
}