package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto;

import com.pragma.plazoletamicroservicio.domain.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    private Long id;
    private String nombre;
    private String apellido;
    private String numeroDocumento;
    private String celular;
    private LocalDate fechaNacimiento;
    private String correo;
    private String clave;
    private Rol rol;
}
