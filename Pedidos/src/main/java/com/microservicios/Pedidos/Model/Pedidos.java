package com.microservicios.Pedidos.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedidosId;
    private int codProducto;
    private double total;
    private int unidades;
    private LocalDateTime fechaPedido;

    public Pedidos(int codProducto,  int unidades) {
        this.codProducto = codProducto;
        this.unidades = unidades;
        this.fechaPedido = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        this.fechaPedido = LocalDateTime.now();
    }


    public Pedidos() {
    }

    public Long getPedidosId() {
        return pedidosId;
    }


    public int getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }
}
