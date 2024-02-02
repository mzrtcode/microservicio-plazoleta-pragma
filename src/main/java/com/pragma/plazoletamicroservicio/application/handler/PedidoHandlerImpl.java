package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.*;
import com.pragma.plazoletamicroservicio.application.exception.PedidoInvalidException;
import com.pragma.plazoletamicroservicio.application.mapper.IPedidoMapper;
import com.pragma.plazoletamicroservicio.domain.api.*;
import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import com.pragma.plazoletamicroservicio.domain.model.*;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.UsuarioDto;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.EmpleadoRestauranteEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PedidoEntity;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;
import com.pragma.plazoletamicroservicio.infrastructure.security.jwt.AutenticacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class PedidoHandlerImpl implements IPedidoHandler {

    private final IUsuarioServicePort usuarioServicePort;
    private final IPlatoServicePort platoServicePort;
    private final IRestauranteServicePort restauranteServicePort;
    private final IPedidoServicePort pedidoServicePort;
    private final IPedidoPlatoServicePort pedidoPlatoServicePort;
    private final AutenticacionService autenticacionService;
    private final IPedidoMapper pedidoMapper;
    private final IEmpleadoRestauranteServicePort empleadoRestauranteServicePort;


    @Override
    public void crearPedidoInDB(PedidoRequest pedidoRequest) throws PedidoInvalidException, RestauranteNotFoundException, PlatoNoExiste {

        // Extraer el id del usuario de la sesion actual
        Long idClienteSesion = autenticacionService.obtenerUsuarioSesionActual().getId();

        // Validar que el idChef existe y tiene el rol de EMPLEADO
        UsuarioDto usuarioChef = usuarioServicePort.getUsuarioPorId(pedidoRequest.getIdChef());
        if (!usuarioChef.getRol().name().equals("EMPLEADO")) {
            throw new PedidoInvalidException("El usuario no existe o el su rol no es EMPLEADO");
        }

        /* Validar que el cliente pueda realizar otro pedido solo si todos sus pedidos
        anteriores se encuentran en el estado "ENTREGADO" o "CANCELADO". */
        List<EstadoPedido> estados = Arrays.asList(EstadoPedido.PENDIENTE, EstadoPedido.EN_PREPARACION, EstadoPedido.LISTO);
        if(pedidoServicePort.existsByIdClienteAndEstadoPedidoIn(idClienteSesion,estados)){
            throw new PedidoInvalidException("No puedes crear un nuevo pedido mientras tienes uno en 'PENDIENTE', 'PREPARACION' o 'LISTO'");
        }

        // Traer el Restaurante por el ID
        Restaurante restaurante = restauranteServicePort.getRestauranteById(pedidoRequest.getRestauranteId());

        // Verificar que el pedido tenga al menos un plato
        List<ListaPlatosPedido> listaPlatosPedido = pedidoRequest.getListaPlatosPedido();
        if (listaPlatosPedido.isEmpty()) {
            throw new PedidoInvalidException("El pedido debe al menos tener un plato");
        }

        // Validar que los IDs de platos no se repitan
        Set<Long> idPlatosSet = new HashSet<>();
        for (ListaPlatosPedido platoPedido : listaPlatosPedido) {
            Long idPlato = platoPedido.getIdPlato();

            // Validar que cada plato exista
            if (!platoServicePort.platoExistsById(idPlato)) {
                throw new PedidoInvalidException("El plato con id: " + idPlato + " no existe");
            }

            // Validar que la cantidad de los platos sea >= 1
            if (!(platoPedido.getCantidad() >= 1)) {
                throw new PedidoInvalidException("La cantidad de platos por pedido debe ser mayor que 0");
            }

            // Validar que el ID del plato no se repita
            if (!idPlatosSet.add(idPlato)) {
                throw new PedidoInvalidException("El ID del plato se repite en la lista");
            }

            // Validar que los platos pertenezcan al mismo restaurante que el restaurante del pedido
            Long platoRestauranteId = platoServicePort.obtenerPlatoPorId(idPlato).get().getRestaurante().getId();
            if (!Objects.equals(platoRestauranteId, pedidoRequest.getRestauranteId())) {
                throw new PedidoInvalidException("El plato " + idPlato + " no pertenece al restaurante " + platoRestauranteId);
            }

        }




        // 1 Creamos el pedido en la base de datos
        Pedido pedido = new Pedido();
        pedido.setIdCliente(idClienteSesion);
        pedido.setIdChef(pedidoRequest.getIdChef());
        pedido.setFecha(LocalDateTime.now());
        pedido.setEstadoPedido(EstadoPedido.PENDIENTE);
        pedido.setRestaurante(restaurante);
        PedidoEntity pedidoEntity = pedidoServicePort.savePedido(pedido);
        //Colocamos el id del Pedido creado para poder registar el pedido en pedidos_platos
        pedido.setId(pedidoEntity.getId());



        /* 2 Con el id del pedido creado por cada plato entonces creamos un registro en PEDIDOS_PLATOS
        iteramos los id de los platos y en todos colocamos el id del pedido al que pertenecen */

        for (ListaPlatosPedido platoPedido : listaPlatosPedido) {
            Optional<Plato> platoOptional = platoServicePort.obtenerPlatoPorId(platoPedido.getIdPlato());

            PedidoPlato pedidoPlato = new PedidoPlato();
            pedidoPlato.setPedido(pedido);
            pedidoPlato.setPlato(platoOptional.get());
            pedidoPlato.setCantidad(platoPedido.getCantidad());
            pedidoPlatoServicePort.savePedidoPlato(pedidoPlato);
        }

    }

    @Override
    public PedidoResponse listarPedidosPorRestauranteEmpleado(EstadoPedido estadoPedido, int pageNo, int pageSize) throws PedidoInvalidException {

        // Extraer el id del usuario de la sesion actual
        Long idClienteSesion = autenticacionService.obtenerUsuarioSesionActual().getId();


        Pageable pageable = PageRequest.of(pageNo, pageSize);

        //COMPROBAR QUE EL EXISTA UN RESTAURANTE ASOCIADO AL EMPLEADO
        Optional<EmpleadoRestauranteEntity> restaurante = empleadoRestauranteServicePort.findRestauranteByIdEmpleado(idClienteSesion);
        if(restaurante.isEmpty()) throw new PedidoInvalidException("El empleado no esta asociado a ningun restaurante");


        Long idRestaurante = restaurante.get().getRestaurante().getId();
        Page<Pedido> pagePedidos = pedidoServicePort.listarPedidosPorRestauranteEmpleado(idRestaurante, estadoPedido, pageable);

        List<Pedido> pedidosList = pagePedidos.getContent();


        List<PedidoDto> content = pedidoMapper.toPedidoDtoList(pedidosList);
        content.forEach(itemPedido -> {
           Long idPedido = itemPedido.getId();
            List<PedidoPlato> listaPedidoPlatos = pedidoPlatoServicePort.findByPedidoEntityId(idPedido);
            List<Plato> listaPlatos = new ArrayList<Plato>();
            itemPedido.setPlatos(listaPlatos);

            listaPedidoPlatos.forEach(itemPedidoPlato -> {
                Plato plato = itemPedidoPlato.getPlato();
                System.out.println(plato);
                listaPlatos.add(plato);
            });

        });

        PedidoResponse pedidoResponse = new PedidoResponse();
        pedidoResponse.setContent(content);
        pedidoResponse.setPageNo(pagePedidos.getNumber());
        pedidoResponse.setPageSize(pagePedidos.getSize());
        pedidoResponse.setTotalElements(pagePedidos.getTotalElements());
        pedidoResponse.setTotalPages(pagePedidos.getTotalPages());
        pedidoResponse.setLast(pagePedidos.isLast());

        return pedidoResponse;
    }


}
