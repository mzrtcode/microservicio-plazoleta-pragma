package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper;

import com.pragma.plazoletamicroservicio.domain.model.Plato;
import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.domain.model.Rol;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PlatoEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.RestauranteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlatoEntityMapper {

    IPlatoEntityMapper INSTANCE = Mappers.getMapper(IPlatoEntityMapper.class);

    PlatoEntity toEntity(Plato plato);

    Plato toDto(PlatoEntity platoEntity);

    List<Plato> toPlatoList(List<PlatoEntity> platoEntityList);


    default Optional<Plato> toOptionalPlato(Optional<PlatoEntity> optionalPlatoEntity) {
        return optionalPlatoEntity.map(this::toDto);
    }

    default Optional<PlatoEntity> toOptionalPlatoEntity(Optional<Plato> optionalPlato) {
        return optionalPlato.map(this::toEntity);
    }
}