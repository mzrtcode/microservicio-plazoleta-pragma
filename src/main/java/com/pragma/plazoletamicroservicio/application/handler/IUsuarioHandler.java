package com.pragma.plazoletamicroservicio.application.handler;

import com.pragma.plazoletamicroservicio.application.dto.EmpleadoRequest;
import com.pragma.plazoletamicroservicio.application.exception.UsuarioNoRegistradoException;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.UsuarioDto;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception.RestauranteNotFoundException;

public interface IUsuarioHandler {

    UsuarioDto crearEmpleado(EmpleadoRequest empleadoRequest) throws RestauranteNotFoundException, UsuarioNoRegistradoException;


}
