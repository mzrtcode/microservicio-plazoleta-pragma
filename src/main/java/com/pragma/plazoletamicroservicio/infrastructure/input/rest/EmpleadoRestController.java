package com.pragma.plazoletamicroservicio.infrastructure.input.rest;

import com.pragma.plazoletamicroservicio.application.dto.EmpleadoRequest;
import com.pragma.plazoletamicroservicio.application.exception.UsuarioNoRegistradoException;
import com.pragma.plazoletamicroservicio.application.handler.IUsuarioHandler;
import com.pragma.plazoletamicroservicio.domain.api.IUsuarioServicePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/empleados")
@RequiredArgsConstructor
public class EmpleadoRestController {

    private final IUsuarioHandler usuarioHandler;

    @PostMapping
    public ResponseEntity<Void> crearEmpleado(@Valid @RequestBody EmpleadoRequest empleadoRequest) throws UsuarioNoRegistradoException, RestauranteNotFoundException {
        usuarioHandler.crearEmpleado(empleadoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
