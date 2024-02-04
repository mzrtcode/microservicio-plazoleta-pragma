package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.feignclient;

import com.pragma.plazoletamicroservicio.infrastructure.configuration.FeignClientConfig;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.UsuarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "MICROSERVICIO-USUARIOS", url = "${microservicio-usuarios-url}", configuration = FeignClientConfig.class)
public interface IUsuarioFeignClient {


    @GetMapping(value = "/usuarios/rol?rol={nombreRol}", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<UsuarioDto> getUsuariosPorRol(@RequestParam("nombreRol") String rol);


    @GetMapping(value = "/usuarios/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    UsuarioDto getUsuarioPorId(@PathVariable Long id);

    @PostMapping(value = "/usuarios", consumes = MediaType.APPLICATION_JSON_VALUE)
    UsuarioDto crearEmpleado(@RequestBody UsuarioDto usuarioDto);

}
