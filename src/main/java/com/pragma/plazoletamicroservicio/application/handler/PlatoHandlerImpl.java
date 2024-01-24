package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.PlatoRequest;
import com.pragma.plazoletamicroservicio.application.mapper.IPlatoMapper;
import com.pragma.plazoletamicroservicio.domain.api.IPlatoServicePort;
import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import com.pragma.plazoletamicroservicio.domain.model.Plato;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlatoHandlerImpl implements  IPlatoHandler{

    private final IPlatoServicePort platoServicePort;
    private final IPlatoMapper platoMapper;
    @Override
    public void savePlatoInDB(PlatoRequest platoRequest) {
        Plato plato = platoMapper.toPlato(platoRequest);
        platoServicePort.savePlato(plato);
    }

    @Override
    public void actualizarPlatoInDB(PlatoRequest platoRequest, Long id) throws PlatoNoExiste {
        Optional<Plato> platoOptional = platoServicePort.obtenerPlatoPorId(id);
        if(platoOptional.isEmpty()) throw new PlatoNoExiste("El plano no existe");


        Plato plato = platoOptional.get();
        plato.setDescription(platoRequest.getDescription());
        plato.setPrecio(platoRequest.getPrecio());
        platoServicePort.savePlato(plato);
    }
}
