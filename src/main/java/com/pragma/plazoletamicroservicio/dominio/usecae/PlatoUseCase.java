package com.pragma.plazoletamicroservicio.dominio.usecae;

import com.pragma.plazoletamicroservicio.dominio.api.IPedidoServicePort;
import com.pragma.plazoletamicroservicio.dominio.api.IPlatoServicePort;
import com.pragma.plazoletamicroservicio.dominio.model.Plato;
import com.pragma.plazoletamicroservicio.dominio.spi.IPlatoPersistencePort;
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
