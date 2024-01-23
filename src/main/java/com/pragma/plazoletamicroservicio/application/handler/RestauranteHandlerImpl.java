package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.RestauranteRequest;
import com.pragma.plazoletamicroservicio.application.dto.RestauranteResponse;
import com.pragma.plazoletamicroservicio.application.mapper.IRestauranteMapper;
import com.pragma.plazoletamicroservicio.domain.api.IRestauranteServicePort;
import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RestauranteHandlerImpl implements IRestauranteHandler{

    private final IRestauranteServicePort restauranteServicePort;
    private final IRestauranteMapper restauranteMapper;
    @Override
    public void saveRestaurantInDB(RestauranteRequest restauranteRequest) {
       restauranteMapper.toRestaurante(restauranteRequest);
    }

    @Override
    public List<RestauranteResponse> getAllRestaurantsFromDB() {
        List<Restaurante> restaurantesList = restauranteServicePort.getAllRestaurantes();
        return restauranteMapper.toRestauranteResponse(restaurantesList);
    }

    @Override
    public void updateRestaurantInDB(RestauranteRequest restauranteRequest, Long id) {

    }

    @Override
    public void deleteRestaurantFromDB(Long id) {

    }
}
