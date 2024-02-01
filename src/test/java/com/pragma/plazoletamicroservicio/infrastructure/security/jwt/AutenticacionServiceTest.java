package com.pragma.plazoletamicroservicio.infrastructure.security.jwt;

import com.pragma.plazoletamicroservicio.infrastructure.security.jwt.dto.UsuarioAutenticado;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AutenticacionServiceTest {

    @Test
    void obtenerUsuarioSesionActual_UsuarioAutenticado() {
        // Arrange
        Long usuarioId = 20L;
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sIjoiQURNSU5JU1RSQURPUiIsInN1YiI6IjEyMzQ1Njc4OSIsImlhdCI6MTcwNjU2Mzg0NCwiZXhwIjoxNzA2NjUwMjQ0fQ.4IHP1ezFD9vAUWkrGItOHUsvM2enQqDCe8ql5i_EyAE";

        UsuarioAutenticado usuarioAutenticado = new UsuarioAutenticado(usuarioId, authorities, token);
        Authentication authentication = new UsernamePasswordAuthenticationToken(usuarioAutenticado, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AutenticacionService autenticacionService = new AutenticacionService();

        // Act
        UsuarioAutenticado resultUsuario = autenticacionService.obtenerUsuarioSesionActual();

        // Assert
        assertNotNull(resultUsuario);
        assertEquals(usuarioAutenticado, resultUsuario);
    }

    @Test
    void obtenerUsuarioSesionActual_NoUsuarioAutenticado() {
        // Arrange
        SecurityContextHolder.clearContext(); // Limpiar el contexto de seguridad

        AutenticacionService autenticacionService = new AutenticacionService();

        // Act
        UsuarioAutenticado resultUsuario = autenticacionService.obtenerUsuarioSesionActual();

        // Assert
        assertNull(resultUsuario);
    }
}