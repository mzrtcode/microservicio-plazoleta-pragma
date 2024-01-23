package com.pragma.plazoletamicroservicio.domain.model;


public class PedidoPlato {

    private Long id;

    private Pedido pedido;
    private Plato plato;
    private Integer cantidad;

    public PedidoPlato() {
    }

    public PedidoPlato(Long id, Pedido pedido, Plato plato, Integer cantidad) {
        this.id = id;
        this.pedido = pedido;
        this.plato = plato;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
