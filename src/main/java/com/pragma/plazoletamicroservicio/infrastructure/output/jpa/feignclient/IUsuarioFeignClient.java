package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.feignclient;

import com.pragma.plazoletamicroservicio.infrastructure.configuration.FeignClientConfig;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.UsuarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "USUARIO-MOCK-API", url = "${external-mock-api-url}", configuration = FeignClientConfig.class)
public interface IUsuarioFeignClient {


    @GetMapping(value = "/usuarios/rol?rol={nombreRol}", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<UsuarioDto> getUsuariosPorRol(@RequestParam("nombreRol") String rol);


    @GetMapping(value = "/usuarios/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    UsuarioDto getUsuarioPorId(@PathVariable Long id);


}
