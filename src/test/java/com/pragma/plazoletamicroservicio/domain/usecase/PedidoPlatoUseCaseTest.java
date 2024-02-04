package com.pragma.plazoletamicroservicio.domain.usecase;

import com.pragma.plazoletamicroservicio.domain.model.PedidoPlato;
import com.pragma.plazoletamicroservicio.domain.spi.IPedidoPlatoPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoPlatoUseCaseTest {

    @Mock
    private IPedidoPlatoPersistencePort  platoPersistencePort;

    @InjectMocks
    private PedidoPlatoUseCase platoUseCase;
    @Test
    void savePedidoPlato() {
        PedidoPlato pedidoPlato = new PedidoPlato();

        // ACT
        platoUseCase.savePedidoPlato(pedidoPlato);

        // ASSERT
        verify(platoPersistencePort, times(1)).savePedidoPlato(pedidoPlato);
    }

    @Test
    void findByPedidoEntityId() {
        Long idPedidoEntity = 30L;
        PedidoPlato pedidoPlato = new PedidoPlato();
        List<PedidoPlato> listaPedidoPlatos = Arrays.asList(pedidoPlato);

        when(platoPersistencePort.findByPedidoEntityId(idPedidoEntity)).thenReturn(listaPedidoPlatos);

        // ACT
        List<PedidoPlato> resultado = platoUseCase.findByPedidoEntityId(idPedidoEntity);

        // ASSERT
        assertEquals(listaPedidoPlatos, resultado);

    }
}