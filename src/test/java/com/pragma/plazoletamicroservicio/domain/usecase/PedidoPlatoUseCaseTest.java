package com.pragma.plazoletamicroservicio.domain.usecase;

import com.pragma.plazoletamicroservicio.domain.model.PedidoPlato;
import com.pragma.plazoletamicroservicio.domain.spi.IPedidoPlatoPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
}