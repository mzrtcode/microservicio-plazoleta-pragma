package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.RestauranteRequest;
import com.pragma.plazoletamicroservicio.application.dto.RestauranteResponse;
import com.pragma.plazoletamicroservicio.application.exception.UsuarioInvalidException;
import com.pragma.plazoletamicroservicio.application.mapper.IRestauranteMapper;
import com.pragma.plazoletamicroservicio.domain.api.IRestauranteServicePort;
import com.pragma.plazoletamicroservicio.domain.api.IUsuarioServicePort;
import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.domain.model.Rol;
import com.pragma.plazoletamicroservicio.domain.spi.IUsuarioPersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestauranteHandlerImpl implements IRestauranteHandler{

    private final IRestauranteServicePort restauranteServicePort;
    private final IUsuarioPersistencePort usuarioServicePort;
    private final IRestauranteMapper restauranteMapper;
    @Override
    public void saveRestaurantInDB(RestauranteRequest restauranteRequest) throws UsuarioInvalidException {

        //Validar que el propietario existe
        UsuarioDto usuario = usuarioServicePort.getUsuarioPorId(restauranteRequest.getIdPropietario());
        if(usuario != null && !usuario.getRol().equals(Rol.PROPIETARIO)) throw new UsuarioInvalidException("El usuario no cumple con el rol de ADMINISTRADOR");

        Restaurante restaurante = restauranteMapper.toRestaurante(restauranteRequest);
        restaurante.setIdPropietario(usuario.getId());
        restauranteServicePort.saveRestaurante(restaurante);
    }


}
