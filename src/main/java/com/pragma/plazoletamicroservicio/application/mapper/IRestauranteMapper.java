package com.pragma.plazoletamicroservicio.application.mapper;

import com.pragma.plazoletamicroservicio.application.dto.RestauranteRequest;
import com.pragma.plazoletamicroservicio.application.dto.RestauranteResponse;
import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestauranteMapper {

    Restaurante toRestaurante(RestauranteRequest restauranteRequest);
    RestauranteRequest toRestauranteRequest  (Restaurante restaurante);

    Restaurante toRestaurante(RestauranteResponse restauranteResponse);
    RestauranteResponse toRestauranteResponse (Restaurante restaurante);

}
