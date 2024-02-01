package com.pragma.plazoletamicroservicio.infrastructure.security.jwt;

import com.pragma.plazoletamicroservicio.infrastructure.security.jwt.dto.UsuarioAutenticado;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtAuthenticationManager jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String token = getTokenFromRequest(request);

        if (token == null){
            filterChain.doFilter(request, response);
            return;
        }

        if(jwtService.isTokenValid(token)){
            //Guardar usuario en el security context
            UsuarioAutenticado usuarioAutenticado = jwtService.generarUsuarioToken(token);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    usuarioAutenticado,
                    null,
                    usuarioAutenticado.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
            filterChain.doFilter(request,response);
        }
    }   



    private String getTokenFromRequest(HttpServletRequest request) {
        //Sacar la cabecera de autenticacion
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){
            // Extraer texto desde el caracter 7 para quitar la palabra Bearer y quedarnos con el Token
            return authHeader.substring(7);
        }
        return null;
    }
}