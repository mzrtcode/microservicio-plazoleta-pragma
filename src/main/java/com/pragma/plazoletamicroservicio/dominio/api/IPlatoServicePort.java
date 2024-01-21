package com.pragma.plazoletamicroservicio.dominio.api;

import com.pragma.plazoletamicroservicio.dominio.model.Plato;
import java.util.List;

public interface IPlatoServicePort {

    void saveCategoria(Plato plato);

    List<Plato> getAllCategoria();

    Plato getCategoriaById(Long id);

    void updateCategoria(Plato plato);

    void deleteCategoria(Long id);
}
