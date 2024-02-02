package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.EmpleadoRequest;
import com.pragma.plazoletamicroservicio.application.exception.UsuarioNoRegistradoException;
import com.pragma.plazoletamicroservicio.domain.api.IRestauranteServicePort;
import com.pragma.plazoletamicroservicio.domain.api.IUsuarioServicePort;
import com.pragma.plazoletamicroservicio.domain.model.EmpleadoRestaurante;
import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import com.pragma.plazoletamicroservicio.domain.model.Rol;
import com.pragma.plazoletamicroservicio.domain.spi.IEmpleadoRestaurantePersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.UsuarioDto;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioHandler {

    private final IEmpleadoRestaurantePersistencePort empleadoRestaurantePersistencePort;
    private final IRestauranteServicePort restauranteServicePort;
    private final IUsuarioServicePort usuarioServicePort;

    public UsuarioDto crearEmpleado(EmpleadoRequest empleadoRequest) throws RestauranteNotFoundException, UsuarioNoRegistradoException {

        UsuarioDto empleado = new UsuarioDto();
        empleado.setRol(Rol.EMPLEADO);
        empleado.setCelular(empleadoRequest.getCelular());
        empleado.setCorreo(empleadoRequest.getCorreo());
        empleado.setClave(empleadoRequest.getClave());
        empleado.setFechaNacimiento(empleadoRequest.getFechaNacimiento());
        empleado.setNumeroDocumento(empleadoRequest.getNumeroDocumento());
        empleado.setApellido(empleadoRequest.getApellido());


        // VALIDAR SI EL RESTAURANTE EXISTE
        Restaurante restaurante = restauranteServicePort.getRestauranteById(empleadoRequest.getIdRestaurante());

        // Registar el usuario tipo empleado en el MICROSERVICIO USUARIOS
        UsuarioDto empleadoCreado;
        try {
            empleadoCreado = usuarioServicePort.crearEmpleado(empleado);
        } catch (Exception e) {
            throw new UsuarioNoRegistradoException("Error al registrar el usuario en el microservicio de usuarios.");
        }

        // Si el empleado se registró en el microservicio de usuarios y su id
        // de restaurante es válido, procedemos a registrarlo en empleado_restaurante
        Long empleadoCreadoId = empleadoCreado.getId();


        //Crear el objeto a registrar
        EmpleadoRestaurante empleadoRestaurante = new EmpleadoRestaurante();
        empleadoRestaurante.setIdEmpleado(empleadoCreadoId);
        empleadoRestaurante.setRestaurante(restaurante);

        empleadoRestaurantePersistencePort.registrarEmpleadoRestaurante(empleadoRestaurante);


        return empleadoCreado;
    }


}
