package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper;

import com.pragma.plazoletamicroservicio.domain.model.Categoria;
import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.domain.model.Rol;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.CategoriaEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.RestauranteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoriaEntityMapper {

    CategoriaEntity toEntity(Categoria categoria);

}
