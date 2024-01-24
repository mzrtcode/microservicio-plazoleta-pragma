package com.pragma.plazoletamicroservicio.domain.api;

import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import com.pragma.plazoletamicroservicio.domain.model.Plato;
import java.util.List;
import java.util.Optional;

public interface IPlatoServicePort {

    void savePlato(Plato plato);

    Optional<Plato> obtenerPlatoPorId(Long id) throws PlatoNoExiste;

    void actualizarPlato(Plato plato, Long id) throws PlatoNoExiste;
}
