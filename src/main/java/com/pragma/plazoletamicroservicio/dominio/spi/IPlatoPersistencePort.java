package com.pragma.plazoletamicroservicio.dominio.spi;

import com.pragma.plazoletamicroservicio.dominio.model.Plato;

import java.util.List;

public interface IPlatoPersistencePort {

    void savePlato(Plato plato);

    List<Plato> getAllPlatos();

    Plato getCategoriaById(Long id);

    void updatePlato(Plato plato);

    void deletePlato(Long id);
}
