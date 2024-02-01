package com.pragma.plazoletamicroservicio.application.mapper;

import com.pragma.plazoletamicroservicio.application.dto.RestauranteRequest;
import com.pragma.plazoletamicroservicio.application.dto.RestauranteDTO;
import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestauranteMapper {

    Restaurante toRestaurante(RestauranteRequest restauranteRequest);
    RestauranteRequest toRestauranteRequest  (Restaurante restaurante);

    Restaurante toRestaurante(RestauranteDTO restauranteDTO);
    RestauranteDTO toRestauranteResponse (Restaurante restaurante);

    List<RestauranteDTO> toRestauranteDtoList(List<Restaurante> restauranteList);

}
