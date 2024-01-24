package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.RestauranteRequest;
import com.pragma.plazoletamicroservicio.application.exception.RestauranteInvalidException;

public interface IRestauranteHandler {

    void saveRestaurantInDB(RestauranteRequest restauranteRequest) throws RestauranteInvalidException;

}
