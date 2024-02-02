package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.adatper;

import com.pragma.plazoletamicroservicio.domain.model.EmpleadoRestaurante;
import com.pragma.plazoletamicroservicio.domain.spi.IEmpleadoRestaurantePersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.EmpleadoRestauranteEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper.IEmpleadoRestauranteMapper;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository.IEmpleadoRestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmpleadoRestauranteJpaAdapter implements IEmpleadoRestaurantePersistencePort {

    private final IEmpleadoRestauranteRepository empleadoRestauranteRepository;
    private final IEmpleadoRestauranteMapper empleadoRestauranteMapper;


    @Override
    public EmpleadoRestauranteEntity registrarEmpleadoRestaurante(EmpleadoRestaurante empleadoRestaurante) {

        EmpleadoRestauranteEntity entity = empleadoRestauranteMapper.tooEntity(empleadoRestaurante);
        return empleadoRestauranteRepository.save(entity);
    }
}
