package com.pragma.plazoletamicroservicio.infrastructure.input.rest;

import com.pragma.plazoletamicroservicio.application.dto.RestauranteRequest;
import com.pragma.plazoletamicroservicio.application.exception.UsuarioInvalidException;
import com.pragma.plazoletamicroservicio.application.handler.IRestauranteHandler;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurantes")
@AllArgsConstructor
public class RestauranteRestController {

    private final IRestauranteHandler restauranteHandler;

    @PostMapping
    public ResponseEntity crearRestaurante(@Valid @RequestBody RestauranteRequest restauranteRequest) throws UsuarioInvalidException {
        restauranteHandler.saveRestaurantInDB(restauranteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
