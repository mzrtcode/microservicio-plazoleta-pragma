package com.pragma.plazoletamicroservicio.application.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoRequest {


    @NotBlank(message = "El nombre es requerido")
    private String nombre;
    @NotBlank(message = "El apellido es requerido")
    private String apellido;

    @NotBlank(message = "El numero de documento es requerido")
    @Pattern(regexp = "^\\d+$", message = "Solo se aceptan numeros") // Valida que sea solo numeros

    private String numeroDocumento;
    @NotBlank(message = "El celular es requerido")
    private String celular;

    @DateTimeFormat
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El correo es requerido")
    @Email
    private String correo;

    @NotBlank(message = "La clave es requerida")
    private String clave;

    // Campo Añadido al modelo de Usuario
    @NotNull
    private Long idRestaurante;

}
