package com.pragma.plazoletamicroservicio.domain.api;

import com.pragma.plazoletamicroservicio.domain.model.Plato;
import java.util.List;

public interface IPlatoServicePort {

    void savePlato(Plato plato);
}
