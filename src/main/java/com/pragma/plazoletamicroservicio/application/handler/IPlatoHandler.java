package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.CategoriaRequest;
import com.pragma.plazoletamicroservicio.application.dto.PlatoRequest;

public interface IPlatoHandler {

    void savePlatoInDB(PlatoRequest platoRequest);
}
