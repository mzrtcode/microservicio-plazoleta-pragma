package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.adatper;

import com.pragma.plazoletamicroservicio.domain.api.ICategoriaServicePort;
import com.pragma.plazoletamicroservicio.domain.model.Categoria;
import com.pragma.plazoletamicroservicio.domain.spi.ICategoriaPersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.CategoriaEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper.ICategoriaEntityMapper;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository.ICategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaJpaAdapter implements ICategoriaPersistencePort {

    private final ICategoriaRepository categoriaRepository;
    private final ICategoriaEntityMapper categoriaEntityMapper;

    @Override
    public void saveCategoria(Categoria categoria) {
        CategoriaEntity categoriaEntity = categoriaEntityMapper.toEntity(categoria);
        categoriaRepository.save(categoriaEntity);
    }
}
