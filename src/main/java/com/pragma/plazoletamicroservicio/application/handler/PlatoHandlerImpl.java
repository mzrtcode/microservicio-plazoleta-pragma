package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.PlatoRequest;
import com.pragma.plazoletamicroservicio.application.mapper.IPlatoMapper;
import com.pragma.plazoletamicroservicio.domain.api.IPlatoServicePort;
import com.pragma.plazoletamicroservicio.domain.api.IRestauranteServicePort;
import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import com.pragma.plazoletamicroservicio.domain.model.Plato;
import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;
import com.pragma.plazoletamicroservicio.infrastructure.security.jwt.dto.UsuarioToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlatoHandlerImpl implements  IPlatoHandler{

    private final IPlatoServicePort platoServicePort;
    private final IRestauranteServicePort restauranteServicePort;
    private final IPlatoMapper platoMapper;
    @Override
    public void savePlatoInDB(PlatoRequest platoRequest) {
        Plato plato = platoMapper.toPlato(platoRequest);
        platoServicePort.savePlato(plato);
    }

    @Override
    public void actualizarPlatoInDB(PlatoRequest platoRequest, Long id) throws PlatoNoExiste, RestauranteNotFoundException {

        //Validar que el plato a modificar pertenesca al restaurante del propietario
        // traer el plato con el id que dio el usuario y de ese plato sacar el restaurante id
        // traer el restaurante y mirar el id del propietario condincide con el id del usuario con session actual
        Optional<Plato> platoOptional = platoServicePort.obtenerPlatoPorId(id);
        if(platoOptional.isEmpty()) throw new PlatoNoExiste("El plato no existe");

        Restaurante restaurante = restauranteServicePort.getRestauranteById(platoOptional.get().getId());
        if(restaurante.getIdPropietario() != obtenerIdPrincipal()) throw new RestauranteNotFoundException("El restaurante pertenece a otro propietario");


        Plato plato = platoOptional.get();

        if(platoRequest.getDescription() != null){
            plato.setDescription(platoRequest.getDescription());
        }

        if(platoRequest.getPrecio() != null){
            plato.setPrecio(platoRequest.getPrecio());

        }

        if(platoRequest.getActivo() != null){
            plato.setActivo(platoRequest.getActivo());
        }

        platoServicePort.savePlato(plato);
    }

    private Long obtenerIdPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UsuarioToken) {
            UsuarioToken usuarioToken = (UsuarioToken) authentication.getPrincipal();
            return usuarioToken.getId();
        }

        return 0L;
    }
}
