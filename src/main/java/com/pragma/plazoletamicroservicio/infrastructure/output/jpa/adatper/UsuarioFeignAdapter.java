package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.adatper;

import com.pragma.plazoletamicroservicio.domain.spi.IUsuarioPersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.TrazabilidadDto;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.UsuarioDto;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.feignclient.IUsuarioFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioFeignAdapter implements IUsuarioPersistencePort {

    private final IUsuarioFeignClient usuarioFeignClient;

    @Override
    public List<UsuarioDto> getUsuariosPorRol(String rol) {
        return usuarioFeignClient.getUsuariosPorRol(rol);
    }

    @Override
    public UsuarioDto getUsuarioPorId(Long id) {
        return usuarioFeignClient.getUsuarioPorId(id);
    }

    @Override
    public UsuarioDto crearEmpleado(UsuarioDto empleado) {
        return usuarioFeignClient.crearEmpleado(empleado);
    }


}
