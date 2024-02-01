package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.ListaPlatosPedido;
import com.pragma.plazoletamicroservicio.application.dto.PedidoRequest;
import com.pragma.plazoletamicroservicio.application.exception.PedidoInvalidException;
import com.pragma.plazoletamicroservicio.application.mapper.IPedidoMapper;
import com.pragma.plazoletamicroservicio.domain.api.*;
import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import com.pragma.plazoletamicroservicio.domain.model.*;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.UsuarioDto;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PedidoEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;
import com.pragma.plazoletamicroservicio.infrastructure.security.jwt.AutenticacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoHandlerImpl implements PedidoHandler{

    private final IUsuarioServicePort usuarioServicePort;
    private final IPlatoServicePort platoServicePort;
    private final IRestauranteServicePort restauranteServicePort;
    private final IPedidoServicePort pedidoServicePort;
    private final IPedidoPlatoServicePort pedidoPlatoServicePort;
    private final IPedidoMapper pedidoMapper;
    private final AutenticacionService autenticacionService;

    @Override
    public void crearPedidoInDB(PedidoRequest pedidoRequest) throws PedidoInvalidException, RestauranteNotFoundException, PlatoNoExiste {



        // Validar que el Usuario existe y tiene el rol de EMPLEADO
        UsuarioDto usuarioChef = usuarioServicePort.getUsuarioPorId(pedidoRequest.getIdChef());
        if(!usuarioChef.getRol().name().equals("EMPLEADO")) {
            throw  new PedidoInvalidException("El usuario no existe o el Rol no se EMPLEADO");
        }

        //Traer el Restaurante por el ID
        Restaurante restaurante = restauranteServicePort.getRestauranteById(pedidoRequest.getRestauranteId());

        //Verificar que el pedido tenga almenos un plato
        List<ListaPlatosPedido> listaPlatosPedido = pedidoRequest.getListaPlatosPedido();
        if(listaPlatosPedido.isEmpty()) {
            throw new PedidoInvalidException("El pedido debe almenos tener un plato");
        }

        // Validaciones para los platos
        for(ListaPlatosPedido platoPedido: listaPlatosPedido){
            Long idPlato = platoPedido.getIdPlato();

            // Validar que cada plato exista
            if(!platoServicePort.platoExistsById(idPlato)){
                throw new PedidoInvalidException("El plato con id: " + idPlato + "no existe");
            }

            //Validar que la cantidad de los platos sea >= 1
            if(platoPedido.getCantidad() >= 1){
                throw new PedidoInvalidException("La cantidad de platos por pedido debe ser mayor que 0");
            }
        }



        // 1 Creamos el pedido en la base de datos
        Pedido pedido = new Pedido();
        pedido.setIdCliente(autenticacionService.obtenerUsuarioSesionActual().getId());
        pedido.setIdChef(pedidoRequest.getIdChef());
        pedido.setFecha(LocalDateTime.now());
        pedido.setEstadoPedido(EstadoPedido.PENDIENTE);
        pedido.setRestaurante(restaurante);
        PedidoEntity pedidoEntity = pedidoServicePort.savePedido(pedido);
        //Colocamos el id del Pedido creado para poder registar el pedido en pedidos_platos
        pedido.setId(pedidoEntity.getId());


        // 2 Una vez creado el pedido le sacamos el id al pedido creado
        Long idPedido = pedidoEntity.getId();


        // 3 Con el id del pedido creado por cada plato entonces creamos un registro en PEDIDOS_PLATOS
        // iteramos los id de los platos y en todos colocamos el id del pedido al que pertenecen
        // pedidoPlatoServicePort
        for(ListaPlatosPedido platoPedido: listaPlatosPedido){
            Optional<Plato> platoOptional = platoServicePort.obtenerPlatoPorId(platoPedido.getIdPlato());


            PedidoPlato pedidoPlato = new PedidoPlato();
            pedidoPlato.setPedido(pedido);
            pedidoPlato.setPlato(platoOptional.get());
            pedidoPlato.setCantidad(platoPedido.getCantidad());

            pedidoPlatoServicePort.savePedidoPlato(pedidoPlato);
        }


    }
}
