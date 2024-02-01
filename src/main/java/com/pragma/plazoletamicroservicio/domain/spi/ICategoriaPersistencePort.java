package com.pragma.plazoletamicroservicio.domain.spi;


import com.pragma.plazoletamicroservicio.domain.model.Categoria;

import java.util.List;

public interface ICategoriaPersistencePort {

    void saveCategoria(Categoria categoria);


}
