package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.adatper;

import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.UsuarioDto;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.feignclient.IUsuarioFeignClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioFeignAdapterTest {

    @Mock
    private IUsuarioFeignClient usuarioFeignClient;

    @InjectMocks
    private UsuarioFeignAdapter usuarioFeignAdapter;

    @Test
    void getUsuariosPorRol() {
        // Arrange
        String rol = "admin";
        List<UsuarioDto> usuariosMock = Arrays.asList(new UsuarioDto(), new UsuarioDto());

        when(usuarioFeignClient.getUsuariosPorRol(rol)).thenReturn(usuariosMock);

        // Act
        List<UsuarioDto> resultUsuarios = usuarioFeignAdapter.getUsuariosPorRol(rol);

        // Assert
        assertEquals(usuariosMock, resultUsuarios);
        verify(usuarioFeignClient).getUsuariosPorRol(rol);
    }

    @Test
    void getUsuarioPorId() {
        // Arrange
        Long id = 1L;
        UsuarioDto usuarioMock = new UsuarioDto();

        when(usuarioFeignClient.getUsuarioPorId(id)).thenReturn(usuarioMock);

        // Act
        UsuarioDto resultUsuario = usuarioFeignAdapter.getUsuarioPorId(id);

        // Assert
        assertEquals(usuarioMock, resultUsuario);
        verify(usuarioFeignClient).getUsuarioPorId(id);
    }

    @Test
    void crearEmpleado() {
        // Datos de prueba
        UsuarioDto empleadoDto = new UsuarioDto();
        UsuarioDto expectedEmpleadoDto = new UsuarioDto(); // Puedes ajustar esto según el resultado esperado

        // Configuración del mock del cliente Feign
        when(usuarioFeignClient.crearEmpleado(empleadoDto)).thenReturn(expectedEmpleadoDto);

        // ACT
        UsuarioDto result = usuarioFeignAdapter.crearEmpleado(empleadoDto);

        // ASSERT
        verify(usuarioFeignClient).crearEmpleado(empleadoDto);
        assertEquals(expectedEmpleadoDto, result);}

}