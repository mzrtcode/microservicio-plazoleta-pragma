package com.pragma.plazoletamicroservicio.infrastructure.input.rest;

import com.pragma.plazoletamicroservicio.application.dto.PedidoRequest;
import com.pragma.plazoletamicroservicio.application.dto.PedidoResponse;
import com.pragma.plazoletamicroservicio.application.exception.PedidoInvalidException;
import com.pragma.plazoletamicroservicio.application.handler.IPedidoHandler;
import com.pragma.plazoletamicroservicio.application.handler.IPedidoPlatoHandlerImpl;
import com.pragma.plazoletamicroservicio.domain.api.IPedidoPlatoServicePort;
import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import com.pragma.plazoletamicroservicio.domain.model.EstadoPedido;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PedidoEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository.IPedidoRepository;
import com.pragma.plazoletamicroservicio.infrastructure.security.jwt.AutenticacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
@RequiredArgsConstructor
public class PedidoRestController {

    private final IPedidoHandler pedidoHandler;
    private final AutenticacionService autenticacionService;


    @PostMapping
    public ResponseEntity<Void> crearPedido(@Valid @RequestBody PedidoRequest pedidoRequest) throws RestauranteNotFoundException, PlatoNoExiste, PedidoInvalidException {
        pedidoHandler.crearPedidoInDB(pedidoRequest);
        return ResponseEntity.ok().build();
    }

    //NUEVO
    @GetMapping
    @PreAuthorize("hasAuthority('EMPLEADO')")
    public PedidoResponse listarPedidosPorEstado(
            @RequestParam(value = "estado", defaultValue = "", required = true) EstadoPedido estadoPedido,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){

        Long idUsuarioActual = autenticacionService.obtenerUsuarioSesionActual().getId();


        return pedidoHandler.findByEstadoPedidoAndIdChef(estadoPedido, idUsuarioActual, pageNo, pageSize);

    }

}
