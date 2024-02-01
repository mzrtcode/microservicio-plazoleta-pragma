package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.CategoriaRequest;

public interface ICategoriaHandler {

    void saveCategoriaInDB(CategoriaRequest categoriaRequest);

}
