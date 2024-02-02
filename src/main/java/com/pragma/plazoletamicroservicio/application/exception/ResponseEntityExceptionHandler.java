package com.pragma.plazoletamicroservicio.application.exception;

import com.pragma.plazoletamicroservicio.application.dto.ErrorMessage;
import com.pragma.plazoletamicroservicio.domain.exception.PlatoNoExiste;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseEntityExceptionHandler {

    @ExceptionHandler(PlatoNoExiste.class)
    public ResponseEntity<ErrorMessage> platoNoExisteHandler(PlatoNoExiste ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorMessage errorMessage = new ErrorMessage(httpStatus, ex.getMessage());
        return ResponseEntity.status(httpStatus).body(errorMessage);
    }

    @ExceptionHandler(PedidoInvalidException.class)
    public ResponseEntity<ErrorMessage> pedidoInvalidExceptionHandler(PedidoInvalidException ex){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorMessage errorMessage = new ErrorMessage(httpStatus, ex.getMessage());
        return ResponseEntity.status(httpStatus).body(errorMessage);
    }

    @ExceptionHandler(UsuarioNoRegistradoException.class)
    public ResponseEntity<ErrorMessage> UsuarioNoRegistradoExceptionHandler(UsuarioNoRegistradoException ex){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorMessage errorMessage = new ErrorMessage(httpStatus, ex.getMessage());
        return ResponseEntity.status(httpStatus).body(errorMessage);
    }


}
