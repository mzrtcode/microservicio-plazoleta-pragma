package com.pragma.plazoletamicroservicio.domain.model;

public class EmpleadoRestaurante {

    private Long idEmpleado;
    Restaurante restaurante;

    public EmpleadoRestaurante() {
    }

    public EmpleadoRestaurante(Long idEmpleado, Restaurante restaurante) {
        this.idEmpleado = idEmpleado;
        this.restaurante = restaurante;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}
