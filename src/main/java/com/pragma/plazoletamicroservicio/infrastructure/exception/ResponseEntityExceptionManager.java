package com.pragma.plazoletamicroservicio.infrastructure.exception;

import com.pragma.plazoletamicroservicio.application.dto.ErrorMessage;
import com.pragma.plazoletamicroservicio.application.exception.RestauranteInvalidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseEntityExceptionManager {

    private static Logger logger = LoggerFactory.getLogger(ResponseEntityExceptionManager.class);

    @ExceptionHandler(RestauranteInvalidException.class)
    public ResponseEntity<ErrorMessage> handleRestauranteInvalidException(RestauranteInvalidException ex){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorMessage message = new ErrorMessage(status, ex.getMessage());
        return ResponseEntity.status(status).body(message);
    }


}
