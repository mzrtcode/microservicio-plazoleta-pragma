package com.pragma.plazoletamicroservicio.domain.usecae;

import com.pragma.plazoletamicroservicio.domain.api.IUsuarioServicePort;
import com.pragma.plazoletamicroservicio.domain.spi.IUsuarioPersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UsuarioUseCase implements IUsuarioServicePort {

    private final IUsuarioPersistencePort usuarioPersistencePort;
    @Override
    public List<UsuarioDto> getUsuariosPorRol(String rol) {
        return usuarioPersistencePort.getUsuariosPorRol(rol);
    }

    @Override
    public UsuarioDto getUsuarioPorId(Long id) {
        return usuarioPersistencePort.getUsuarioPorId(id);
    }
}
