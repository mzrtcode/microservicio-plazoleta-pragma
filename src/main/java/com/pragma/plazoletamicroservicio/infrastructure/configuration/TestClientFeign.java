package com.pragma.plazoletamicroservicio.infrastructure.configuration;

import com.pragma.plazoletamicroservicio.domain.spi.IUsuarioPersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TestClientFeign implements CommandLineRunner {

    private final IUsuarioPersistencePort usuarioPersistencePort;

    @Override
    public void run(String... args) throws Exception {
        List<UsuarioDto> usuariosPorRol = usuarioPersistencePort.getUsuariosPorRol("CLIENTE");
        System.out.println(usuariosPorRol);
    }
}
