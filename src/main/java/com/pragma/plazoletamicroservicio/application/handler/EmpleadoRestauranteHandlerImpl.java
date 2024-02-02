package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.domain.api.IEmpleadoRestauranteServicePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.EmpleadoRestauranteEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmpleadoRestauranteHandlerImpl implements IEmpleadoRestauranteHandler{


    private final IEmpleadoRestauranteServicePort empleadoRestauranteServicePort;

    public Optional<EmpleadoRestauranteEntity> findRestauranteByIdEmpleado (Long idEmpleado){
        return empleadoRestauranteServicePort.findRestauranteByIdEmpleado(idEmpleado);
    }


}
