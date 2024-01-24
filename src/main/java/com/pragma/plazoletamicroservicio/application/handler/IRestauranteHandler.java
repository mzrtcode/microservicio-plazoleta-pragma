package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.RestauranteRequest;
import com.pragma.plazoletamicroservicio.application.dto.RestauranteResponse;
import com.pragma.plazoletamicroservicio.application.exception.UsuarioInvalidException;

import java.util.List;

public interface IRestauranteHandler {

    void saveRestaurantInDB(RestauranteRequest restauranteRequest) throws UsuarioInvalidException;

}
