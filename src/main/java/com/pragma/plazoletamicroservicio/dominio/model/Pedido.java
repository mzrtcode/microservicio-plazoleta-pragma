package com.pragma.plazoletamicroservicio.dominio.model;

import java.time.LocalDateTime;

public class Pedido {

    private Long id;

    private Long idCliente;

    private LocalDateTime fecha;

    private Long idChef;

    private Restaurante restaurante;

    public Pedido() {
    }

    public Pedido(Long id, Long idCliente, LocalDateTime fecha, Long idChef, Restaurante restaurante) {
        this.id = id;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.idChef = idChef;
        this.restaurante = restaurante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Long getIdChef() {
        return idChef;
    }

    public void setIdChef(Long idChef) {
        this.idChef = idChef;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}
