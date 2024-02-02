package com.pragma.plazoletamicroservicio.domain.api;

import com.pragma.plazoletamicroservicio.application.dto.EmpleadoRequest;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.UsuarioDto;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;

import java.util.List;

public interface IUsuarioServicePort {

    List<UsuarioDto> getUsuariosPorRol(String rol);
    UsuarioDto getUsuarioPorId(Long id);
    UsuarioDto crearEmpleado (UsuarioDto usuarioDto);
}
