package com.pragma.plazoletamicroservicio.infrastructure.input.rest;

import com.pragma.plazoletamicroservicio.application.dto.PlatoRequest;
import com.pragma.plazoletamicroservicio.application.handler.IPlatoHandler;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
