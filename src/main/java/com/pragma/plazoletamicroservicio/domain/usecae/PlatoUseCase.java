package com.pragma.plazoletamicroservicio.domain.usecae;

import com.pragma.plazoletamicroservicio.domain.api.IPlatoServicePort;
import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import com.pragma.plazoletamicroservicio.domain.model.Plato;
import com.pragma.plazoletamicroservicio.domain.spi.IPlatoPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PlatoUseCase implements IPlatoServicePort {

    private final IPlatoPersistencePort platoPersistencePort;

    @Override
    public void savePlato(Plato plato) {
        platoPersistencePort.savePlato(plato);
    }

    @Override
    public Optional<Plato> obtenerPlatoPorId(Long id) throws PlatoNoExiste {
        return platoPersistencePort.obtenerPlatoPorId(id);
    }

    @Override
    public void actualizarPlato(Plato plato, Long id) throws PlatoNoExiste {
        Optional<Plato> platoOptional = platoPersistencePort.obtenerPlatoPorId(id);

        if(platoOptional.isEmpty()) throw new PlatoNoExiste("El plato no existe");
        Plato platoActualizar = platoOptional.get();
        platoActualizar.setPrecio(plato.getPrecio());
        platoActualizar.setDescription(plato.getDescription());

        platoPersistencePort.savePlato(platoActualizar);
    }

    @Override
    public List<Plato> getPlatosByRestauranteId(Long id) {
        return platoPersistencePort.getPlatosByRestauranteId(id);
    }


}
