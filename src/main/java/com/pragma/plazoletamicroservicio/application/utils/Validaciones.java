package com.pragma.plazoletamicroservicio.application.utils;

public class Validaciones {

    private Validaciones(){

    }

    public static boolean esNumero(String cadena) {
        // Utilizando una expresión regular para verificar si la cadena contiene solo números
        return cadena.matches("\\d+");
    }

    public static boolean esTelefonoValido(String telefono) {
        // Validar que el teléfono contenga solo números y tenga un máximo de 13 caracteres
        return telefono.matches("^\\+?\\d{1,13}$");
    }

    public static boolean esNombreRestauranteValido(String nombre) {
        // Validar que el nombre del restaurante puede contener números,
        // pero no se permiten nombres con sólo números
        return nombre.matches(".*[a-zA-Z].*\\d*") && !nombre.matches("\\d+");
    }


}
