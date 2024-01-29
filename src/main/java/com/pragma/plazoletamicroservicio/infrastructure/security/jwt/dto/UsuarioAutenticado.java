package com.pragma.plazoletamicroservicio.infrastructure.security.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioAutenticado {
    private Long id;
    private List<GrantedAuthority> authorities;
    private String token;


}
