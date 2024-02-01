package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.RestauranteRequest;
import com.pragma.plazoletamicroservicio.application.dto.RestauranteDTO;
import com.pragma.plazoletamicroservicio.application.dto.RestauranteResponse;
import com.pragma.plazoletamicroservicio.application.exception.RestauranteInvalidException;
import com.pragma.plazoletamicroservicio.application.mapper.IRestauranteMapper;
import com.pragma.plazoletamicroservicio.domain.api.IRestauranteServicePort;
import com.pragma.plazoletamicroservicio.domain.api.IUsuarioServicePort;
import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.domain.model.Rol;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.metamodel.mapping.PluralAttributeMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public RestauranteResponse getAllRestaurantes(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Restaurante> pageRestaurantes = restauranteServicePort.getAllRestaurantes(pageable);
        List<Restaurante> restauranteList = pageRestaurantes.getContent();

        List<RestauranteDTO> content = restauranteMapper.toRestauranteDtoList(restauranteList);

        RestauranteResponse restauranteResponse = new RestauranteResponse();
        restauranteResponse.setContent(content);
        restauranteResponse.setPageNo(pageRestaurantes.getNumber());
        restauranteResponse.setPageSize(pageRestaurantes.getSize());
        restauranteResponse.setTotalElements(pageRestaurantes.getTotalElements());
        restauranteResponse.setTotalPages(pageRestaurantes.getTotalPages());
        restauranteResponse.setLast(pageRestaurantes.isLast());

        return restauranteResponse;

    }

}
