package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.CategoriaRequest;
import com.pragma.plazoletamicroservicio.application.dto.PlatoRequest;
import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;

public interface IPlatoHandler {

    void savePlatoInDB(PlatoRequest platoRequest);

    void actualizarPlatoInDB(PlatoRequest platoRequest, Long id) throws PlatoNoExiste;
}
