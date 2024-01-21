package com.pragma.plazoletamicroservicio.dominio.api;


import com.pragma.plazoletamicroservicio.dominio.model.Restaurante;
import java.util.List;

public interface IRestauranteServicePort {

    void saveRestaurante(Restaurante restaurante);

    List<Restaurante> getAllRestaurantes();

    Restaurante getRestauranteById(Long id);

    void updateRestaurante(Restaurante restaurante);

    void deleteRestaurante(Long id);
}
