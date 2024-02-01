package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.adatper;

import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.RestauranteEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.mapper.IRestauranteEntityMapper;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository.IRestauranteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Test
    void getRestauranteById_NoExiste() {
        // Arrange
        Long idRestaurante = 1L;

        when(restauranteRepository.findById(idRestaurante)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RestauranteNotFoundException.class,
                () -> restauranteJpaAdapter.getRestauranteById(idRestaurante));

        verify(restauranteRepository).findById(idRestaurante);
    }

    @Test
    void getRestauranteById_Existe() throws RestauranteNotFoundException {
        // Arrange
        Long idRestaurante = 1L;
        RestauranteEntity restauranteEntity = new RestauranteEntity();
        Restaurante restaurante = new Restaurante();

        when(restauranteRepository.findById(idRestaurante)).thenReturn(Optional.of(restauranteEntity));
        when(restauranteMapper.toRestaurante(restauranteEntity)).thenReturn(restaurante);

        // Act
        Restaurante resultRestaurante = restauranteJpaAdapter.getRestauranteById(idRestaurante);

        // Assert
        assertEquals(restaurante, resultRestaurante);
        verify(restauranteRepository).findById(idRestaurante);
        verify(restauranteMapper).toRestaurante(restauranteEntity);
    }

    @Test
    void getAllRestaurantesTest() {
        // Preparación
        Pageable pageable = PageRequest.of(0, 5);
        List<RestauranteEntity> restaurantEntities = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            RestauranteEntity entity = new RestauranteEntity();
            entity.setNombre("Restaurant" + i);
            restaurantEntities.add(entity);
        }
        Page<RestauranteEntity> entities = new PageImpl<>(restaurantEntities);

        when(restauranteRepository.findAll(pageable)).thenReturn(entities);

        // Ejecución
        Page<Restaurante> restaurants = restauranteJpaAdapter.getAllRestaurantes(pageable);

        // Verificación
        assertEquals(5, restaurants.getTotalElements());
        assertEquals(5, restaurants.getContent().size());
    }
}