package com.pragma.plazoletamicroservicio.infrastructure.input.rest;

import com.pragma.plazoletamicroservicio.application.dto.PlatoRequest;
import com.pragma.plazoletamicroservicio.application.handler.IPlatoHandler;
import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/platos")
@AllArgsConstructor
public class PlatoRestController {

    private final IPlatoHandler platoHandler;
    @PostMapping
    public ResponseEntity crearPlato(@Valid @RequestBody PlatoRequest platoRequest){
        platoHandler.savePlatoInDB(platoRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity acualizarPlato(@Valid @RequestBody PlatoRequest platoRequest, @PathVariable Long id) throws PlatoNoExiste, RestauranteNotFoundException {
        platoHandler.actualizarPlatoInDB(platoRequest, id);
        return ResponseEntity.ok().build();
    }



}
