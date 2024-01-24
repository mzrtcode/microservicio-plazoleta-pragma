package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.RestauranteRequest;
import com.pragma.plazoletamicroservicio.application.exception.RestauranteInvalidException;
import com.pragma.plazoletamicroservicio.application.mapper.IRestauranteMapper;
import com.pragma.plazoletamicroservicio.application.utils.Validaciones;
import com.pragma.plazoletamicroservicio.domain.api.IRestauranteServicePort;
import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.domain.model.Rol;
import com.pragma.plazoletamicroservicio.domain.spi.IUsuarioPersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestauranteHandlerImpl implements IRestauranteHandler{

    private final IRestauranteServicePort restauranteServicePort;
    private final IUsuarioPersistencePort usuarioServicePort;
    private final IRestauranteMapper restauranteMapper;
    @Override
    public void saveRestaurantInDB(RestauranteRequest restauranteRequest) throws RestauranteInvalidException {

        //Validar que el propietario existe
        UsuarioDto usuario = usuarioServicePort.getUsuarioPorId(restauranteRequest.getIdPropietario());
        if(usuario != null && !usuario.getRol().equals(Rol.PROPIETARIO)) throw new RestauranteInvalidException("El usuario no cumple con el rol de ADMINISTRADOR");

        // Validar que NIT sea numerico
        if(!Validaciones.esNumero(restauranteRequest.getNit())) throw  new RestauranteInvalidException("El NIT debe contener solo numeros");

        // Validar que el telefono sea valido
        if(!Validaciones.esTelefonoValido(restauranteRequest.getTelefono())) throw new RestauranteInvalidException("El telefono no es valido, debe ser solo numero");

        // Validar numbre restaurante
        if(!Validaciones.esNombreRestauranteValido(restauranteRequest.getNombre())) throw new RestauranteInvalidException("El nombre del restaurante no es valido");









        Restaurante restaurante = restauranteMapper.toRestaurante(restauranteRequest);
        restaurante.setIdPropietario(usuario.getId());
        restauranteServicePort.saveRestaurante(restaurante);
    }


}
