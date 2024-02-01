package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.CategoriaRequest;
import com.pragma.plazoletamicroservicio.application.mapper.ICategoriaMapper;
import com.pragma.plazoletamicroservicio.domain.api.ICategoriaServicePort;
import com.pragma.plazoletamicroservicio.domain.model.Categoria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaHandlerImpTest {

    @Mock
    private ICategoriaServicePort categoriaServicePort;
    @Mock
    private ICategoriaMapper categoriaMapper;

    @InjectMocks
    private CategoriaHandlerImp categoriaHandlerImp;

    @Test
    void saveCategoriaInDB() {
        CategoriaRequest categoriaRequest = new CategoriaRequest();
        Categoria categoria = new Categoria();

        when(categoriaMapper.toCategoria(categoriaRequest)).thenReturn(categoria);

        //ACT
        categoriaHandlerImp.saveCategoriaInDB(categoriaRequest);

        //ASSERT
        verify(categoriaServicePort, times(1)).saveCategoria(categoria);
    }
}