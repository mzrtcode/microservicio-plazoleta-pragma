package com.pragma.plazoletamicroservicio.application.mapper;

import com.pragma.plazoletamicroservicio.application.dto.PlatoRequest;
import com.pragma.plazoletamicroservicio.application.dto.PlatoDTO;
import com.pragma.plazoletamicroservicio.domain.model.Plato;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlatoMapper {


    Plato toPlato(PlatoRequest platoRequest);
    PlatoRequest toPlatoRequest  (Plato plato);

    Plato toPlato(PlatoDTO platoDTO);
    PlatoDTO toPlatoDto (Plato plato);

    List<PlatoDTO> toPlatoDtoList(List<Plato> platoList);

}
