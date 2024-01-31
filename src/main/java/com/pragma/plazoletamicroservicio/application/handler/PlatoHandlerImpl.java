package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.PlatoDTO;
import com.pragma.plazoletamicroservicio.application.dto.PlatoRequest;
import com.pragma.plazoletamicroservicio.application.dto.PlatoResponse;
import com.pragma.plazoletamicroservicio.application.mapper.IPlatoMapper;
import com.pragma.plazoletamicroservicio.domain.api.IPlatoServicePort;
import com.pragma.plazoletamicroservicio.domain.api.IRestauranteServicePort;
import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import com.pragma.plazoletamicroservicio.domain.model.Plato;
import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;
import com.pragma.plazoletamicroservicio.infrastructure.security.jwt.AutenticacionService;
import com.pragma.plazoletamicroservicio.infrastructure.security.jwt.dto.UsuarioAutenticado;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlatoHandlerImpl implements  IPlatoHandler{

    private final IPlatoServicePort platoServicePort;
    private final IRestauranteServicePort restauranteServicePort;
    private final IPlatoMapper platoMapper;
    private final AutenticacionService autenticacionService;
    @Override
    public void savePlatoInDB(PlatoRequest platoRequest) {
        Plato plato = platoMapper.toPlato(platoRequest);
        platoServicePort.savePlato(plato);
    }

    @Override
    public void actualizarPlatoInDB(PlatoRequest platoRequest, Long id) throws PlatoNoExiste, RestauranteNotFoundException {

        Optional<Plato> platoOptional = platoServicePort.obtenerPlatoPorId(id);
        if(platoOptional.isEmpty()) throw new PlatoNoExiste("El plato no existe");

        Restaurante restaurante = restauranteServicePort.getRestauranteById(platoOptional.get().getRestaurante().getId());
        UsuarioAutenticado usuarioAutenticado = autenticacionService.obtenerUsuarioSesionActual();
        if(!Objects.equals(restaurante.getIdPropietario(), usuarioAutenticado.getId())) throw new RestauranteNotFoundException("El restaurante pertenece a otro propietario");


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

    @Override
    public PlatoResponse getPlatosByRestauranteId(Long id, int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Plato> pagePlatos = platoServicePort.getPlatosByRestauranteId(id, pageable);
        List<Plato> platosList = pagePlatos.getContent();

        List<PlatoDTO> content = platoMapper.toPlatoDtoList(platosList);

        PlatoResponse platoResponse = new PlatoResponse();
        platoResponse.setContent(content);
        platoResponse.setPageNo(pagePlatos.getNumber());
        platoResponse.setPageSize(pagePlatos.getSize());
        platoResponse.setTotalElements(pagePlatos.getTotalElements());
        platoResponse.setTotalPages(pagePlatos.getTotalPages());
        platoResponse.setLast(pagePlatos.isLast());

        return platoResponse;


    }


}
