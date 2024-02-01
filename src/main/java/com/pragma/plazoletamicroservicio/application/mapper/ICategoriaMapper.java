package com.pragma.plazoletamicroservicio.application.mapper;

import com.pragma.plazoletamicroservicio.application.dto.CategoriaRequest;
import com.pragma.plazoletamicroservicio.application.dto.CategoriaResponse;
import com.pragma.plazoletamicroservicio.domain.model.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoriaMapper {

    Categoria toCategoria(CategoriaRequest categoriaRequest);
    CategoriaRequest toCategoriaRequest (Categoria categoria);

    Categoria toCategoria(CategoriaResponse categoriaResponse);
    CategoriaResponse toCategoriaResponse (Categoria categoria);
}
