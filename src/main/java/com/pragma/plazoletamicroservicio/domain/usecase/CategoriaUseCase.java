package com.pragma.plazoletamicroservicio.domain.usecase;

import com.pragma.plazoletamicroservicio.domain.api.ICategoriaServicePort;
import com.pragma.plazoletamicroservicio.domain.model.Categoria;
import com.pragma.plazoletamicroservicio.domain.spi.ICategoriaPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoriaUseCase implements ICategoriaServicePort {

    private final ICategoriaPersistencePort categoriaPersistencePort;

    @Override
    public void saveCategoria(Categoria categoria) {
        categoriaPersistencePort.saveCategoria(categoria);
    }


}
