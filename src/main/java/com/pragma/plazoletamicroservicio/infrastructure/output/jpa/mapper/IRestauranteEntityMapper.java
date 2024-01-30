package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper;

import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.domain.model.Rol;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.RestauranteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestauranteEntityMapper {

    RestauranteEntity toEntity(Restaurante restaurante);
    default Rol toRol(String rol) {
        return Rol.valueOf(rol);
    }
    Restaurante toRestaurante(RestauranteEntity restauranteEntity);

    List<Restaurante> toListRestaurante(List<RestauranteEntity> restauranteEntityList);

    List<RestauranteEntity> toListRestauranteEntity(List<Restaurante> restauranteList);


}
