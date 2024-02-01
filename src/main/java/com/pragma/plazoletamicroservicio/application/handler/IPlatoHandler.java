package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.PlatoRequest;
import com.pragma.plazoletamicroservicio.application.dto.PlatoDTO;
import com.pragma.plazoletamicroservicio.application.dto.PlatoResponse;
import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;

import java.util.List;

public interface IPlatoHandler {

    void savePlatoInDB(PlatoRequest platoRequest);

    void actualizarPlatoInDB(PlatoRequest platoRequest, Long id) throws PlatoNoExiste, RestauranteNotFoundException;

    PlatoResponse getPlatosByRestauranteId(Long id, int pageNo, int pageSize);

    boolean platoExistsById(Long id);

}
