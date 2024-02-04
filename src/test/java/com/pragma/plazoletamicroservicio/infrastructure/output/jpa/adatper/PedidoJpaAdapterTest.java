package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.adatper;

import com.pragma.plazoletamicroservicio.domain.model.EstadoPedido;
import com.pragma.plazoletamicroservicio.domain.model.Pedido;
import com.pragma.plazoletamicroservicio.domain.model.PedidoPlato;
import com.pragma.plazoletamicroservicio.domain.model.Plato;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PedidoEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.feignclient.IMensajeriaFeignClient;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper.IPedidoEntityMapper;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository.IPedidoRepository;
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
class PedidoJpaAdapterTest {

    @Mock
    private IPedidoRepository pedidoRepository;

    @Mock
    private IPedidoEntityMapper pedidoMapper;

    @Mock
    private IMensajeriaFeignClient mensajeriaFeignClient;
    @InjectMocks
    private PedidoJpaAdapter pedidoJpaAdapter;
    @Test
    void savePedido() {
        Pedido pedido = new Pedido();
        PedidoEntity pedidoEntity = new PedidoEntity();

        when(pedidoMapper.toPedidoEntity(pedido)).thenReturn(pedidoEntity);
        when(pedidoRepository.save(pedidoEntity)).thenReturn(pedidoEntity);

        // ACT
        pedidoJpaAdapter.savePedido(pedido);

        // ASSERT
        verify(pedidoRepository, times(1)).save(pedidoEntity);
        verify(pedidoMapper, times(1)).toPedidoEntity(pedido);


    }

    @Test
    void existsByIdClienteAndEstadoPedidoIn() {
        Long idCliente = 22L;
        List<EstadoPedido> listaEstados = Arrays.asList(EstadoPedido.EN_PREPARACION, EstadoPedido.ENTREGADO);
        boolean seEncontro = true;

        when(pedidoRepository.existsByIdClienteAndEstadoPedidoIn(idCliente, listaEstados)).thenReturn(seEncontro);

        // ACT
        boolean resultado = pedidoJpaAdapter.existsByIdClienteAndEstadoPedidoIn(idCliente, listaEstados);

        // ASSERT
        assertEquals(seEncontro, resultado);
        verify(pedidoRepository, times(1)).existsByIdClienteAndEstadoPedidoIn(idCliente, listaEstados);
    }

    @Test
    void listarPedidosPorRestauranteEmpleado() {
        // Datos de prueba
        Long idChef = 20L;
        int pageNo = 1;
        int pageSize = 2;
        EstadoPedido estadoPedido = EstadoPedido.EN_PREPARACION;

        // Mock del repositorio y del mapper
        List<PedidoEntity> pedidoEntityList = Collections.singletonList(new PedidoEntity());
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<PedidoEntity> pagePedidoEntity = new PageImpl<>(pedidoEntityList);

        when(pedidoRepository.findByRestauranteIdAndEstadoPedido(idChef, estadoPedido, pageable)).thenReturn(pagePedidoEntity);

        // Aquí es donde puedes mejorar la lógica del mock del mapper según tu implementación real
        when(pedidoMapper.toPedido(any(PedidoEntity.class))).thenReturn(new Pedido());

        // Llamada al método que estás probando
        Page<Pedido> result = pedidoJpaAdapter.listarPedidosPorRestauranteEmpleado(idChef, estadoPedido, pageable);

        //ASSERT
        verify(pedidoRepository).findByRestauranteIdAndEstadoPedido(idChef, estadoPedido, pageable);
        verify(pedidoMapper, times(pedidoEntityList.size())).toPedido(any(PedidoEntity.class));
    }

    @Test
    void obtenerPedidoPorId() {
        // Datos de prueba
        Long idPedido = 1L;
        PedidoEntity pedidoEntity = new PedidoEntity();
        Optional<PedidoEntity> optionalPedidoEntity = Optional.of(pedidoEntity);
        Optional<Pedido> expectedPedido = Optional.of(new Pedido());

        when(pedidoRepository.findById(idPedido)).thenReturn(optionalPedidoEntity);
        when(pedidoMapper.toPedido(optionalPedidoEntity)).thenReturn(expectedPedido);

        // ACT
        Optional<Pedido> result = pedidoJpaAdapter.obtenerPedidoPorId(idPedido);

        // ASSERT
        verify(pedidoRepository).findById(idPedido);
        verify(pedidoMapper).toPedido(optionalPedidoEntity);
        assertEquals(expectedPedido, result);


    }
    @Test
    void notificarUsuario() {
        // Datos de prueba
        String destinatario = "usuario@example.com";
        String mensaje = "Este es un mensaje de prueba";

        // ACT
        pedidoJpaAdapter.notificarUsuario(destinatario, mensaje);

        // ASSERT
        verify(mensajeriaFeignClient).notificarUsuario(destinatario, mensaje);
    }
}