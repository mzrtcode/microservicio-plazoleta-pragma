package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.feignclient;

import com.pragma.plazoletamicroservicio.infrastructure.configuration.FeignClientConfig;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.TrazabilidadDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MICROSERVICIO-TRAZABILIDAD", url = "${microservicio-trazabilidad-url}", configuration = FeignClientConfig.class)
public interface ITrazabilidadFeignClient {

    @PostMapping(value = "/estadopedido", consumes = MediaType.APPLICATION_JSON_VALUE)
    void crearRegistroEstadoPedido(@RequestBody TrazabilidadDto trazabilidadDto);
}
