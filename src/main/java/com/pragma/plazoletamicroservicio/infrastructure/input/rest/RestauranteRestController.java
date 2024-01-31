package com.pragma.plazoletamicroservicio.infrastructure.input.rest;

import com.pragma.plazoletamicroservicio.application.dto.PlatoDTO;
import com.pragma.plazoletamicroservicio.application.dto.PlatoResponse;
import com.pragma.plazoletamicroservicio.application.dto.RestauranteRequest;
import com.pragma.plazoletamicroservicio.application.dto.RestauranteResponse;
import com.pragma.plazoletamicroservicio.application.exception.RestauranteInvalidException;
import com.pragma.plazoletamicroservicio.application.handler.IPlatoHandler;
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
    private final IPlatoHandler platoHandler;


    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<HttpStatus> crearRestaurante(@Valid @RequestBody RestauranteRequest restauranteRequest) throws RestauranteInvalidException {
        restauranteHandler.saveRestaurantInDB(restauranteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<RestauranteResponse> getAllRestaurantes(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){

        RestauranteResponse restaurantes = restauranteHandler.getAllRestaurantes(pageNo, pageSize);
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/{id}/platos")
    public ResponseEntity<PlatoResponse> getAllByRestauranteId(
            @PathVariable Long id,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        PlatoResponse platoResponse = platoHandler.getPlatosByRestauranteId(id, pageNo, pageSize);
        return ResponseEntity.ok(platoResponse);
    }
}
