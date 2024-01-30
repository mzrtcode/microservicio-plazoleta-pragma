package com.pragma.plazoletamicroservicio.application.mapper;

import com.pragma.plazoletamicroservicio.application.dto.PlatoRequest;
import com.pragma.plazoletamicroservicio.application.dto.PlatoResponse;
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

    Plato toPlato(PlatoResponse platoResponse);
    PlatoResponse toPlatoResponse (Plato plato);

    List<PlatoResponse> toPlatoResponseList(List<Plato> platoList);

}
