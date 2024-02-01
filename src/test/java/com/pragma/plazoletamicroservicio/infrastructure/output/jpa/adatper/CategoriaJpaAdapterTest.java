package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.adatper;

import com.pragma.plazoletamicroservicio.domain.model.Categoria;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.CategoriaEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper.ICategoriaEntityMapper;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository.ICategoriaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaJpaAdapterTest {

    @Mock
    private  ICategoriaRepository categoriaRepository;

    @Mock
    private  ICategoriaEntityMapper categoriaEntityMapper;

    @InjectMocks
    private CategoriaJpaAdapter categoriaJpaAdapter;

    @Test
    void saveCategoria() {
        Categoria categoria = new Categoria();
        CategoriaEntity categoriaEntity = new CategoriaEntity();

        when(categoriaEntityMapper.toEntity(categoria)).thenReturn(categoriaEntity);

        //ACT
        categoriaJpaAdapter.saveCategoria(categoria);


        //ASSERT
        verify(categoriaRepository, times(1)).save(categoriaEntity);
    }
}