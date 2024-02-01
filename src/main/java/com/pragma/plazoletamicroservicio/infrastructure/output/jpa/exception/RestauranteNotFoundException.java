package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.exception;

public class RestauranteNotFoundException extends Exception{
    public RestauranteNotFoundException(String message) {
        super(message);
    }
}
