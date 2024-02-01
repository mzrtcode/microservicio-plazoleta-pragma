package com.pragma.plazoletamicroservicio.infrastructure.security.jwt;

import com.pragma.plazoletamicroservicio.infrastructure.security.jwt.dto.UsuarioAutenticado;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService {

    public UsuarioAutenticado obtenerUsuarioSesionActual() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UsuarioAutenticado) {
            return (UsuarioAutenticado) authentication.getPrincipal();
        }

        return null;
    }
}
