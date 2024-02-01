package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.CategoriaRequest;
import com.pragma.plazoletamicroservicio.application.mapper.ICategoriaMapper;
import com.pragma.plazoletamicroservicio.domain.api.ICategoriaServicePort;
import com.pragma.plazoletamicroservicio.domain.model.Categoria;
import com.pragma.plazoletamicroservicio.domain.spi.ICategoriaPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaHandlerImp implements ICategoriaHandler{

    private final ICategoriaServicePort categoriaServicePort;
    private final ICategoriaMapper categoriaMapper;
    @Override
    public void saveCategoriaInDB(CategoriaRequest categoriaRequest) {
        Categoria categoria = categoriaMapper.toCategoria(categoriaRequest);
        categoriaServicePort.saveCategoria(categoria);
    }
}
