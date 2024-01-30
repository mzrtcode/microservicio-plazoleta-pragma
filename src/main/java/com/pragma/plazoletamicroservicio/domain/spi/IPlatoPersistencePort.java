package com.pragma.plazoletamicroservicio.domain.spi;

import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import com.pragma.plazoletamicroservicio.domain.model.Plato;

import java.util.List;
import java.util.Optional;

public interface IPlatoPersistencePort {

    void savePlato(Plato plato);
    Optional<Plato> obtenerPlatoPorId(Long id) throws PlatoNoExiste;

    List<Plato> getPlatosByRestauranteId(Long id);

}
