package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.adatper;

import com.pragma.plazoletamicroservicio.domain.model.Pedido;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PedidoEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper.IPedidoEntityMapper;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository.IPedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.parameters.P;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoJpaAdapterTest {

    @Mock
    private IPedidoRepository pedidoRepository;

    @Mock
    private IPedidoEntityMapper pedidoMapper;

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
}