package com.pragma.plazoletamicroservicio.domain.spi;

import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.UsuarioDto;

import java.util.List;

public interface IUsuarioPersistencePort {

    List<UsuarioDto> getUsuariosPorRol(String rol);

    UsuarioDto getUsuarioPorId(Long id);

    UsuarioDto crearEmpleado(UsuarioDto usuarioDto);
}
