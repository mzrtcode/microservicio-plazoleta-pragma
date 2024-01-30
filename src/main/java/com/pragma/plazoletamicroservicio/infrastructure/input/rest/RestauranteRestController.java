package com.pragma.plazoletamicroservicio.infrastructure.input.rest;

import com.pragma.plazoletamicroservicio.application.dto.RestauranteRequest;
import com.pragma.plazoletamicroservicio.application.dto.RestauranteResponse;
import com.pragma.plazoletamicroservicio.application.exception.RestauranteInvalidException;
import com.pragma.plazoletamicroservicio.application.handler.IRestauranteHandler;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurantes")
@AllArgsConstructor
public class RestauranteRestController {

    private final IRestauranteHandler restauranteHandler;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<HttpStatus> crearRestaurante(@Valid @RequestBody RestauranteRequest restauranteRequest) throws RestauranteInvalidException {
        restauranteHandler.saveRestaurantInDB(restauranteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<RestauranteResponse>> getAllRestaurantes(){
        List<RestauranteResponse> allRestaurantes = restauranteHandler.getAllRestaurantes();
        return ResponseEntity.ok(allRestaurantes);
    }
}
