package com.pragma.plazoletamicroservicio.domain.model;

import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido {

    private Long id;

    private Long idCliente;

    private LocalDateTime fecha;

    private EstadoPedido estadoPedido;

    private Long idChef;

    private Restaurante restaurante;

    private List<PedidoPlato> pedidoPlatos;

    public Pedido() {
    }

    public Pedido(Long id, Long idCliente, LocalDateTime fecha, EstadoPedido estadoPedido, Long idChef, Restaurante restaurante, List<PedidoPlato> pedidoPlatos) {
        this.id = id;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.estadoPedido = estadoPedido;
        this.idChef = idChef;
        this.restaurante = restaurante;
        this.pedidoPlatos = pedidoPlatos;
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

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
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

    public List<PedidoPlato> getPedidoPlatos() {
        return pedidoPlatos;
    }

    public void setPedidoPlatos(List<PedidoPlato> pedidoPlatos) {
        this.pedidoPlatos = pedidoPlatos;
    }
}
