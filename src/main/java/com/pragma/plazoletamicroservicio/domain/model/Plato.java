package com.pragma.plazoletamicroservicio.domain.model;


import java.util.List;

public class Plato {

    private Long id;
    private String nombre;

    private Categoria categoria;

    private String description;

    private Double precio;

    private Restaurante restaurante;

    private String urlImagen;

    private Boolean activo;

    private List<PedidoPlato> pedidoPlato;

    public Plato() {
    }

    public Plato(Long id, String nombre, Categoria categoria, String description, Double precio, Restaurante restaurante, String urlImagen, Boolean activo, List<PedidoPlato> pedidoPlato) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.description = description;
        this.precio = precio;
        this.restaurante = restaurante;
        this.urlImagen = urlImagen;
        this.activo = activo;
        this.pedidoPlato = pedidoPlato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public List<PedidoPlato> getPedidoPlato() {
        return pedidoPlato;
    }

    public void setPedidoPlato(List<PedidoPlato> pedidoPlato) {
        this.pedidoPlato = pedidoPlato;
    }
}
