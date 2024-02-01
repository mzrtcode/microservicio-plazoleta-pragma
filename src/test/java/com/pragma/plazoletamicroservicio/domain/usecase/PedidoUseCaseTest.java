package com.pragma.plazoletamicroservicio.domain.usecase;

import com.pragma.plazoletamicroservicio.domain.model.Pedido;
import com.pragma.plazoletamicroservicio.domain.spi.IPedidoPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.parameters.P;

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

        // ACT
        pedidoUseCase.savePedido(pedido);

        // ASSERT
        verify(pedidoPersistencePort, times(1)).savePedido(pedido);
    }
}