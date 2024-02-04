package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.feignclient;


import com.pragma.plazoletamicroservicio.infrastructure.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "MICROSERVICIO-MENSAJERIA", url = "${microservicio-mensajaria-url}", configuration = FeignClientConfig.class)
public interface IMensajeriaFeignClient {

    @GetMapping(value = "/notificaciones")
    void notificarUsuario(@RequestParam("destino") String destino, @RequestParam("mensaje") String mensaje);
}
