package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.RestauranteDTO;
import com.pragma.plazoletamicroservicio.application.dto.RestauranteRequest;
import com.pragma.plazoletamicroservicio.application.dto.RestauranteResponse;
import com.pragma.plazoletamicroservicio.application.exception.RestauranteInvalidException;
import com.pragma.plazoletamicroservicio.application.mapper.IRestauranteMapper;
import com.pragma.plazoletamicroservicio.domain.api.IRestauranteServicePort;
import com.pragma.plazoletamicroservicio.domain.api.IUsuarioServicePort;
import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.domain.model.Rol;
import com.pragma.plazoletamicroservicio.domain.spi.IUsuarioPersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.UsuarioDto;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestauranteHandlerImplTest {

    @Mock
    private  IRestauranteServicePort restauranteServicePort;
    @Mock
    private IUsuarioServicePort usuarioServicePort;
    @Mock
    private  IRestauranteMapper restauranteMapper;
    @InjectMocks
    private RestauranteHandlerImpl restauranteHandler;



    @Test
    void saveRestaurantInDBUsuarioExisteRolNoValido() throws RestauranteInvalidException {

        RestauranteRequest restauranteRequest = new RestauranteRequest();
        restauranteRequest.setIdPropietario(2L);
        UsuarioDto usuario = new UsuarioDto();
        usuario.setId(2L);
        usuario.setRol(Rol.ADMINISTRADOR);

        when(usuarioServicePort.getUsuarioPorId(restauranteRequest.getIdPropietario())).thenReturn(usuario);
        assertThrows(RestauranteInvalidException.class, () -> {
            // Código que estás probando
            restauranteHandler.saveRestaurantInDB(restauranteRequest);
        });
    }


    @Test
    void saveRestaurantInDBUsuarioExisteRolValido() throws RestauranteInvalidException {

        Long idPropietario = 3L;
        RestauranteRequest restauranteRequest = new RestauranteRequest();
        restauranteRequest.setIdPropietario(idPropietario);
        restauranteRequest.setNit("123456-7");
        restauranteRequest.setTelefono("312144551");
        restauranteRequest.setNombre("Restaurante");
        UsuarioDto usuario = new UsuarioDto();
        Restaurante restaurante = new Restaurante();
        usuario.setId(2L);
        usuario.setRol(Rol.PROPIETARIO);


        //Validar que el propietario existe
        when(usuarioServicePort.getUsuarioPorId(restauranteRequest.getIdPropietario())).thenReturn(usuario);
        when(restauranteMapper.toRestaurante(restauranteRequest)).thenReturn(restaurante);

        //ACTO
        restauranteHandler.saveRestaurantInDB(restauranteRequest);

        // Assert
        verify(restauranteServicePort).saveRestaurante(restaurante);

    }

    @Test
    public void testGetAllRestaurantes() {
        // Datos de prueba
        int pageNo = 0;
        int pageSize = 10;

        List<Restaurante> restauranteList = new ArrayList<>();

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Restaurante> pageRestaurantes = new PageImpl<>(restauranteList, pageable, restauranteList.size());

        // Configurar el comportamiento del mock de restauranteServicePort
        when(restauranteServicePort.getAllRestaurantes(pageable)).thenReturn(pageRestaurantes);

        // Configurar el comportamiento del mock de restauranteMapper
        List<RestauranteDTO> restauranteDtoList = new ArrayList<>(); // Agrega RestauranteDTOs de prueba según tus necesidades
        when(restauranteMapper.toRestauranteDtoList(restauranteList)).thenReturn(restauranteDtoList);

        // Llamar al método que queremos probar
        RestauranteResponse result = restauranteHandler.getAllRestaurantes(pageNo, pageSize);

        // Ejemplo de verificación
        verify(restauranteMapper, times(1)).toRestauranteDtoList(restauranteList);
    }



}
