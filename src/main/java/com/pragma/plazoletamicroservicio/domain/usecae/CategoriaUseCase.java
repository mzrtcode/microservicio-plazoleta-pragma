package com.pragma.plazoletamicroservicio.domain.usecae;

import com.pragma.plazoletamicroservicio.domain.api.ICategoriaServicePort;
import com.pragma.plazoletamicroservicio.domain.model.Categoria;
import com.pragma.plazoletamicroservicio.domain.spi.ICategoriaPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CategoriaUseCase implements ICategoriaServicePort {

    private final ICategoriaPersistencePort categoriaPersistencePort;

    @Override
    public void saveCategoria(Categoria categoria) {
        categoriaPersistencePort.saveCategoria(categoria);
    }

    @Override
    public List<Categoria> getAllCategorias() {
        return categoriaPersistencePort.getAllCategorias();
    }

    @Override
    public Categoria getCategoriaById(Long id) {
        return categoriaPersistencePort.getCategoriaById(id);
    }

    @Override
    public void updateCategoria(Categoria categoria) {
        categoriaPersistencePort.updateCategoria(categoria);
    }

    @Override
    public void deleteCategoria(Long id) {
        categoriaPersistencePort.deleteCategoria(id);
    }
}
