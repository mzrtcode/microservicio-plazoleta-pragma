package com.pragma.plazoletamicroservicio.infrastructure.input.rest;

import com.pragma.plazoletamicroservicio.application.dto.PedidoRequest;
import com.pragma.plazoletamicroservicio.application.exception.PedidoInvalidException;
import com.pragma.plazoletamicroservicio.application.handler.IPedidoHandler;
import com.pragma.plazoletamicroservicio.application.handler.IPedidoPlatoHandlerImpl;
import com.pragma.plazoletamicroservicio.domain.api.IPedidoPlatoServicePort;
import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pedidos")
@RequiredArgsConstructor
public class PedidoRestController {

    private final IPedidoHandler pedidoHandler;

    @PostMapping
    public ResponseEntity<?> crearPedido(@Valid @RequestBody PedidoRequest pedidoRequest) throws RestauranteNotFoundException, PlatoNoExiste, PedidoInvalidException {
        pedidoHandler.crearPedidoInDB(pedidoRequest);
        return ResponseEntity.ok().build();
    }
}
