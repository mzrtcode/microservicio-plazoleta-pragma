package com.pragma.plazoletamicroservicio.infrastructure.exception;

import com.pragma.plazoletamicroservicio.application.dto.ErrorMessage;
import com.pragma.plazoletamicroservicio.application.exception.UsuarioInvalidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseEntityExceptionManager {

    private static Logger logger = LoggerFactory.getLogger(ResponseEntityExceptionManager.class);

    @ExceptionHandler(UsuarioInvalidException.class)
    public ResponseEntity<ErrorMessage> handleUsuarioInvalidException(UsuarioInvalidException ex){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorMessage message = new ErrorMessage(status, ex.getMessage());
        logger.error("No hay usuarios registrados");
        return ResponseEntity.status(status).body(message);
    }


}
