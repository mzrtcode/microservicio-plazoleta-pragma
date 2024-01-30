package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.RestauranteRequest;
import com.pragma.plazoletamicroservicio.application.dto.RestauranteResponse;
import com.pragma.plazoletamicroservicio.application.exception.RestauranteInvalidException;
import com.pragma.plazoletamicroservicio.application.mapper.IRestauranteMapper;
import com.pragma.plazoletamicroservicio.domain.api.IRestauranteServicePort;
import com.pragma.plazoletamicroservicio.domain.api.IUsuarioServicePort;
import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.domain.model.Rol;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestauranteHandlerImpl implements IRestauranteHandler {

    private final IRestauranteServicePort restauranteServicePort;
    private final IUsuarioServicePort usuarioServicePort;
    private final IRestauranteMapper restauranteMapper;


    @Override
    public void saveRestaurantInDB(RestauranteRequest restauranteRequest) throws RestauranteInvalidException {

        // Validar que el propietario existe y el rol es Propietario
        UsuarioDto usuario = usuarioServicePort.getUsuarioPorId(restauranteRequest.getIdPropietario());

        // Verificar si el usuario es nulo o si el rol no es Propietario
        if (usuario == null || !Rol.PROPIETARIO.equals(usuario.getRol())) {
            throw new RestauranteInvalidException("El usuario no es un propietario");
        }

        Restaurante restaurante = restauranteMapper.toRestaurante(restauranteRequest);
        restaurante.setIdPropietario(usuario.getId());

        restauranteServicePort.saveRestaurante(restaurante);
    }

    @Override
    public List<RestauranteResponse> getAllRestaurantes(int pageNo, int pageSize) {
        List<Restaurante> restauranteList = restauranteServicePort.getAllRestaurantes(pageNo, pageSize);
        return restauranteMapper.toRestauranteResponseList(restauranteList);
    }

}
