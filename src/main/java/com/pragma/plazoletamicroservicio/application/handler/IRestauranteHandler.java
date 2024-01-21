package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.RestauranteRequest;
import com.pragma.plazoletamicroservicio.application.dto.RestauranteResponse;

import java.util.List;

public interface IRestauranteHandler {

    void saveRestaurantInDB(RestauranteRequest restauranteRequest);
    List<RestauranteResponse> getAllRestaurantsFromDB();
    void updateRestaurantInDB(RestauranteRequest restauranteRequest, Long id);
    void deleteRestaurantFromDB(Long id);

}
