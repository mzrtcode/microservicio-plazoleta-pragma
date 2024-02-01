package com.pragma.plazoletamicroservicio.domain.usecase;

import com.pragma.plazoletamicroservicio.domain.spi.IUsuarioPersistencePort;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.dto.UsuarioDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioUseCaseTest {

    @Mock
    private  IUsuarioPersistencePort usuarioPersistencePort;

    @InjectMocks
    private UsuarioUseCase usuarioUseCase;

    @Test
    void getUsuariosPorRol() {
        // Datos de prueba
        String rol = "ROLE_USER";
        List<UsuarioDto> usuariosMock = Collections.singletonList(new UsuarioDto(/* Datos de prueba */));

        // Configurar el comportamiento del mock de usuarioPersistencePort
        when(usuarioPersistencePort.getUsuariosPorRol(rol)).thenReturn(usuariosMock);

        // Llamar al método que queremos probar
        List<UsuarioDto> result = usuarioUseCase.getUsuariosPorRol(rol);

        // Verificar las aserciones
        assertEquals(usuariosMock, result);

        // Verificar que se llamó al menos una vez al método getUsuariosPorRol
        verify(usuarioPersistencePort, times(1)).getUsuariosPorRol(rol);
    }

    @Test
    void getUsuarioPorId() {
        // Datos de prueba
        Long idUsuario = 1L;
        UsuarioDto usuarioMock = new UsuarioDto(/* Datos de prueba */);

        // Configurar el comportamiento del mock de usuarioPersistencePort
        when(usuarioPersistencePort.getUsuarioPorId(idUsuario)).thenReturn(usuarioMock);

        // Llamar al método que queremos probar
        UsuarioDto result = usuarioUseCase.getUsuarioPorId(idUsuario);

        // Verificar las aserciones
        assertEquals(usuarioMock, result);

        // Verificar que se llamó al menos una vez al método getUsuarioPorId
        verify(usuarioPersistencePort, times(1)).getUsuarioPorId(idUsuario);
    }

}