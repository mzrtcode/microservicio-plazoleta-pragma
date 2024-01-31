package com.pragma.plazoletamicroservicio.domain.spi;

import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import com.pragma.plazoletamicroservicio.domain.model.Plato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IPlatoPersistencePort {

    void savePlato(Plato plato);
    Optional<Plato> obtenerPlatoPorId(Long id) throws PlatoNoExiste;

    Page<Plato> getPlatosByRestauranteId(Long id, Pageable pageable);

    boolean platoExistsById(Long id);

}
