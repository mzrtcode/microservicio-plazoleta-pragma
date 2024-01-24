package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.adatper;

import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.RestauranteEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper.IRestauranteEntityMapper;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository.IRestauranteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestauranteJpaAdapterTest {

    @Mock
    private  IRestauranteRepository restauranteRepository;
    @Mock
    private  IRestauranteEntityMapper restauranteMapper;

    @InjectMocks
    private RestauranteJpaAdapter restauranteJpaAdapter;
    @Test
    void saveRestaurante() {
        Restaurante restaurante = new Restaurante();
        RestauranteEntity restauranteEntity = new RestauranteEntity();

        when(restauranteMapper.toEntity(restaurante)).thenReturn(restauranteEntity);

        //ACTO
        restauranteJpaAdapter.saveRestaurante(restaurante);

        //ASSERT
        verify(restauranteRepository).save(restauranteEntity);
    }
}