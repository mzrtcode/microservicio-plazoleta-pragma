package com.pragma.plazoletamicroservicio.domain.usecase;

import com.pragma.plazoletamicroservicio.domain.api.IEmpleadoRestauranteServicePort;
import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.domain.spi.IEmpleadoRestaurantePersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.EmpleadoRestauranteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoRestauranteUseCase implements IEmpleadoRestauranteServicePort {

    private final IEmpleadoRestaurantePersistencePort empleadoRestaurantePersistencePort;
    @Override
    public Optional<EmpleadoRestauranteEntity>  findRestauranteByIdEmpleado(Long idEmpleado) {
        return empleadoRestaurantePersistencePort.findEmpleadoRestauranteByEmpleadoId(idEmpleado);
    }
}
