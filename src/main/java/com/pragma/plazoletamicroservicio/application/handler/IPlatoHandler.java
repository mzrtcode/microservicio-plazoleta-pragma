package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.CategoriaRequest;
import com.pragma.plazoletamicroservicio.application.dto.PlatoRequest;
import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;

public interface IPlatoHandler {

    void savePlatoInDB(PlatoRequest platoRequest);

    void actualizarPlatoInDB(PlatoRequest platoRequest, Long id) throws PlatoNoExiste, RestauranteNotFoundException;
}
