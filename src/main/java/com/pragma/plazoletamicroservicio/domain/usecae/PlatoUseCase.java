package com.pragma.plazoletamicroservicio.domain.usecae;

import com.pragma.plazoletamicroservicio.domain.api.IPlatoServicePort;
import com.pragma.plazoletamicroservicio.domain.model.Plato;
import com.pragma.plazoletamicroservicio.domain.spi.IPlatoPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PlatoUseCase implements IPlatoServicePort {

    private final IPlatoPersistencePort platoPersistencePort;

    @Override
    public void savePlato(Plato plato) {
        platoPersistencePort.savePlato(plato);
    }

    @Override
    public List<Plato> getAllPlatos() {
        return platoPersistencePort.getAllPlatos();
    }

    @Override
    public Plato getPlatoById(Long id) {
        return platoPersistencePort.getCategoriaById(id);
    }

    @Override
    public void updatePlato(Plato plato) {
        platoPersistencePort.updatePlato(plato);
    }

    @Override
    public void deletePlato(Long id) {
        platoPersistencePort.deletePlato(id);
    }
}
