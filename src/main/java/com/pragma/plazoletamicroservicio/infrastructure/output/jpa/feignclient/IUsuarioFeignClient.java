package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.feignclient;

import com.pragma.plazoletamicroservicio.infrastructure.configuration.FeignClientConfig;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.UsuarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "USUARIO-MOCK-API", url = "${external-mock-api-url}", configuration = FeignClientConfig.class)
public interface IUsuarioFeignClient {

    @GetMapping(value = "/usuarios", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<UsuarioDto> getUsuarios();



}
