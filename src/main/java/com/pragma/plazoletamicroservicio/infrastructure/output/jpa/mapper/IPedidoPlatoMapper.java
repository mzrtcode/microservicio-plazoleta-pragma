package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper;

import com.pragma.plazoletamicroservicio.domain.model.PedidoPlato;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PedidoPlatoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPedidoPlatoMapper {

    PedidoPlatoEntity toPedidoPlatoEntity(PedidoPlato pedidoPlato);
}
