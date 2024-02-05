package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.*;
import com.pragma.plazoletamicroservicio.application.exception.PedidoInvalidException;
import com.pragma.plazoletamicroservicio.application.mapper.IPedidoMapper;
import com.pragma.plazoletamicroservicio.application.mapper.IPlatoMapper;
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
    private final IPlatoMapper platoMapper;

    private static final Random random = new Random();


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
        if (pedidoServicePort.existsByIdClienteAndEstadoPedidoIn(idClienteSesion, estados)) {
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
            if (platoPedido.getCantidad() < 1) {
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


        // Crear PIN aleatorio
        String pin = String.format("%08d", random.nextInt(100000000));


        // 1 Creamos el pedido en la base de datos
        Pedido pedido = new Pedido();
        pedido.setIdCliente(idClienteSesion);
        pedido.setIdChef(pedidoRequest.getIdChef());
        pedido.setFecha(LocalDateTime.now());
        pedido.setEstadoPedido(EstadoPedido.PENDIENTE);
        pedido.setRestaurante(restaurante);
        //generamos y seteamos el pin aleatorio al pedido
        pedido.setCodigoRetiro(pin);
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
        if (restaurante.isEmpty())
            throw new PedidoInvalidException("El empleado no esta asociado a ningun restaurante");


        Long idRestaurante = restaurante.get().getRestaurante().getId();
        Page<Pedido> pagePedidos = pedidoServicePort.listarPedidosPorRestauranteEmpleado(idRestaurante, estadoPedido, pageable);

        List<Pedido> pedidosList = pagePedidos.getContent();

        // Cambiando el contenido de la lista de Pedidos para anidarle los platos dentro
        List<PedidoDto> content = pedidoMapper.toPedidoDtoList(pedidosList);
        content.forEach(itemPedido -> {
            Long idPedido = itemPedido.getId();


            List<PedidoPlato> listaPedidoPlatos = pedidoPlatoServicePort.findByPedidoEntityId(idPedido); //TABLA INTERMEDIA PEDIDOS_PLATOS

            List<PlatoDTO> listaPlatos = new ArrayList<>();

            //Extraemos los platos de PEDIDOS_PLATOS y los ponemos dentro del pedido
            itemPedido.setPlatos(listaPlatos);

            listaPedidoPlatos.forEach(itemPedidoPlato -> {
                Plato plato = itemPedidoPlato.getPlato();
                // Añadimos el plato que trajimos de PEDIDOS_PLATOS para ponerlo en la lista que va dentro de Pedido

                //Mapear el plato de la base de datos al PlatoDto
                PlatoDTO platoDto = platoMapper.toPlatoDto(plato);
                listaPlatos.add(platoDto);
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

    public void actualizarPedido(Long idPedido, ActualizarPedidoRequest actualizarPedidoRequest) throws PedidoInvalidException {
        // 1 OBTENER ROL USUARIO
        Rol rolUsuarioSesion = Rol.valueOf(autenticacionService.obtenerUsuarioSesionActual().getAuthorities().get(0).toString());
        Long idUsuarioSesion = autenticacionService.obtenerUsuarioSesionActual().getId();

        //VALIDAR QUE EL PEDIDO EXISTA
        Optional<Pedido> pedidoOptional = pedidoServicePort.obtenerPedidoPorId(idPedido);

        if (pedidoOptional.isEmpty()) throw new PedidoInvalidException("El id de pedido no existe");
        Pedido pedido = pedidoOptional.get();

        // 1. SI ES EMPLEADO PUEDE TOMAR EL PEDIDO y CAMBIAR EL ESTADO
        if (rolUsuarioSesion == Rol.EMPLEADO) {


            // 1.1 SOLO SE PUEDE CAMBIAR A ESTADO EN_PREPARACION SI ESTA EN PENDIENTE
            if (actualizarPedidoRequest.getEstadoPedido() == EstadoPedido.EN_PREPARACION
                    && pedido.getEstadoPedido() == EstadoPedido.PENDIENTE) {

                pedido.setEstadoPedido(EstadoPedido.EN_PREPARACION);
                pedido.setIdEmpleadoAsignado(idUsuarioSesion);

                pedidoServicePort.savePedido(pedido);
                return;
            }


            // 1.2 SOLO SE PUEDE CAMBIAR A LISTO SI ESTA EN EN_PENDIENTE
            else if (actualizarPedidoRequest.getEstadoPedido() == EstadoPedido.LISTO
                    && pedido.getEstadoPedido() == EstadoPedido.EN_PREPARACION) {

                pedido.setEstadoPedido(EstadoPedido.LISTO);

                UsuarioDto cliente = usuarioServicePort.getUsuarioPorId(pedido.getIdCliente());
                pedidoServicePort.notificarUsuario(cliente.getCelular(),
                        "Buen dia, señor(a) " + cliente.getNombre() + " su pedido " + idPedido + " ya esta listo para recoger. \nRecuerde mostrar el siguiente pin: " + pedido.getCodigoRetiro());
                pedidoServicePort.savePedido(pedido);
                return;
            }

            // 1.3 SOLO SE PUEDE CAMBIAR A ENTREGADO SI ESTA LISTO

            else if (actualizarPedidoRequest.getEstadoPedido() == EstadoPedido.ENTREGADO
                    && pedido.getEstadoPedido() == EstadoPedido.LISTO) {

                String codigoRetiroPedido = pedido.getCodigoRetiro();
                String codigoRetiroRequest = actualizarPedidoRequest.getCodigoRetiro();

                if (!codigoRetiroPedido.equals(codigoRetiroRequest)) {
                    throw new PedidoInvalidException("El código de retiro proporcionado no coincide con el pedido.");
                }

                pedido.setEstadoPedido(EstadoPedido.ENTREGADO);
                pedidoServicePort.savePedido(pedido);
                return;

            }
        }

        // 2. SI ES CLIENTE SOLO PUEDE CAMBIAR EL ESTADO
        if (rolUsuarioSesion == Rol.CLIENTE) {


            // 2.1 SOLO SE PUEDE CAMBIAR EL ESTADO A CANCELADO SI ESTA EN PENDIENTE
            if (!(actualizarPedidoRequest.getEstadoPedido() == EstadoPedido.CANCELADO
                    && pedido.getEstadoPedido() == EstadoPedido.PENDIENTE)) {
                throw new PedidoInvalidException("Solo se pueden cancelar pedidos en estado PENDIENTE");
            }

            pedido.setEstadoPedido(EstadoPedido.CANCELADO);
            pedidoServicePort.savePedido(pedido);
            return;

        }


    }

}
