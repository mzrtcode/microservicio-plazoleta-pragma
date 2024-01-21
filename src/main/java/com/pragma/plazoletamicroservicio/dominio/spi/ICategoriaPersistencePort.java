package com.pragma.plazoletamicroservicio.dominio.spi;


import com.pragma.plazoletamicroservicio.dominio.model.Categoria;

import java.util.List;

public interface ICategoriaPersistencePort {

    void saveCategoria(Categoria categoria);

    List<Categoria> getAllCategorias();

    Categoria getCategoriaById(Long id);

    void updateCategoria(Categoria categoria);

    void deleteCategoria(Long id);


}
