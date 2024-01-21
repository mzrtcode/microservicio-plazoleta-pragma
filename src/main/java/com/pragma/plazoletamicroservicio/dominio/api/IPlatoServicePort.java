package com.pragma.plazoletamicroservicio.dominio.api;

import com.pragma.plazoletamicroservicio.dominio.model.Plato;
import java.util.List;

public interface IPlatoServicePort {

    void savePlato(Plato plato);

    List<Plato> getAllPlatos();

    Plato getPlatoById(Long id);

    void updatePlato(Plato plato);

    void deletePlato(Long id);
}
