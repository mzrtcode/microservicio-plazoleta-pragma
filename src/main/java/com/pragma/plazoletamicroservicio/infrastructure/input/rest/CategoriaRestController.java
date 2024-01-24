package com.pragma.plazoletamicroservicio.infrastructure.input.rest;

import com.pragma.plazoletamicroservicio.application.dto.CategoriaRequest;
import com.pragma.plazoletamicroservicio.application.handler.ICategoriaHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categorias")
@RequiredArgsConstructor
public class CategoriaRestController {

    private final ICategoriaHandler categoriaHandler;
    @PostMapping
    public ResponseEntity crearCategoria(@Valid @RequestBody CategoriaRequest categoriaRequest){
        categoriaHandler.saveCategoriaInDB(categoriaRequest);
        return ResponseEntity.ok().build();
    }
}
