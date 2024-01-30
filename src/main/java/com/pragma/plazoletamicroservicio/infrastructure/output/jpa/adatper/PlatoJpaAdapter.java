package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.adatper;

import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import com.pragma.plazoletamicroservicio.domain.model.Plato;
import com.pragma.plazoletamicroservicio.domain.spi.IPlatoPersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PlatoEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper.IPlatoEntityMapper;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository.IPlatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlatoJpaAdapter implements IPlatoPersistencePort {

    private final IPlatoRepository platoRepository;
    private final IPlatoEntityMapper platoEntityMapper;
    @Override
    public void savePlato(Plato plato) {
        PlatoEntity platoEntity = platoEntityMapper.toEntity(plato);
        platoRepository.save(platoEntity);
    }

    @Override
    public Optional<Plato> obtenerPlatoPorId(Long id) throws PlatoNoExiste {
        return platoEntityMapper.toOptionalPlato(platoRepository.findById(id));
    }

    @Override
    public List<Plato> getPlatosByRestauranteId(Long id) {
        List<PlatoEntity> platoEntityList = platoRepository.findByRestauranteId(id);
        return platoEntityMapper.toPlatoList(platoEntityList);
    }


}
