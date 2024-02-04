package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.adatper;

import com.pragma.plazoletamicroservicio.domain.model.Pedido;
import com.pragma.plazoletamicroservicio.domain.model.PedidoPlato;
import com.pragma.plazoletamicroservicio.domain.model.Plato;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PedidoPlatoEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper.IPedidoPlatoMapper;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository.IPedidoPlatoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoPlatoJpaAdapterTest {

    @Mock
    private IPedidoPlatoRepository pedidoPlatoRepository;
    @Mock
    private IPedidoPlatoMapper pedidoPlatoMapper;

    @InjectMocks
    private PedidoPlatoJpaAdapter platoJpaAdapter;

    @Test
    void testSavePedidoPlato() {
        // Arrange
        PedidoPlato pedidoPlato = new PedidoPlato();
        pedidoPlato.setPedido(new Pedido());
        pedidoPlato.setPlato(new Plato());
        pedidoPlato.setCantidad(2);

        PedidoPlatoEntity pedidoPlatoEntity = new PedidoPlatoEntity();
        when(pedidoPlatoMapper.toPedidoPlatoEntity(pedidoPlato)).thenReturn(pedidoPlatoEntity);
        when(pedidoPlatoRepository.save(pedidoPlatoEntity)).thenReturn(pedidoPlatoEntity);

        // Act
        PedidoPlatoEntity result = platoJpaAdapter.savePedidoPlato(pedidoPlato);

        // Assert
        verify(pedidoPlatoMapper, times(1)).toPedidoPlatoEntity(pedidoPlato);
        verify(pedidoPlatoRepository, times(1)).save(pedidoPlatoEntity);

        // Añade más aserciones según sea necesario para verificar el resultado o cualquier otro comportamiento esperado.
        // Por ejemplo, puedes verificar que la entidad devuelta sea la misma que la entidad esperada.
        assertEquals(pedidoPlatoEntity, result);
    }

    @Test
    void findByPedidoEntityId() {
        // Datos de prueba
        Long idPedido = 1L;
        List<PedidoPlatoEntity> pedidoPlatoEntityList = Collections.singletonList(new PedidoPlatoEntity());
        List<PedidoPlato> expectedPedidoPlatoList = Collections.singletonList(new PedidoPlato());

        when(pedidoPlatoRepository.findByPedidoEntityId(idPedido)).thenReturn(pedidoPlatoEntityList);
        when(pedidoPlatoMapper.toPedidoPlato(pedidoPlatoEntityList)).thenReturn(expectedPedidoPlatoList);

        // ACT
        List<PedidoPlato> result = platoJpaAdapter.findByPedidoEntityId(idPedido);

        // ASSERT
        verify(pedidoPlatoRepository).findByPedidoEntityId(idPedido);
        verify(pedidoPlatoMapper).toPedidoPlato(pedidoPlatoEntityList);
        assertEquals(expectedPedidoPlatoList, result);

    }
}