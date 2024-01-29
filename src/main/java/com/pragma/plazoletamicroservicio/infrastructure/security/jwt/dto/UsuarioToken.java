package com.pragma.plazoletamicroservicio.infrastructure.security.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioToken {
    private Long id;
    private List<GrantedAuthority> authorities;
}
