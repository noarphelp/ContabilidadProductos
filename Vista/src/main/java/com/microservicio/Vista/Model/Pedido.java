package com.microservicio.Vista.Model;

import java.time.LocalDateTime;

public class Pedido {
    private Long pedidosId;
    private int codProducto;
    private double total;
    private int unidades;
    private LocalDateTime fechaPedido;

    public Pedido() {
    }

    public Long getPedidosId() {
        return pedidosId;
    }

    public void setPedidosId(Long pedidosId) {
        this.pedidosId = pedidosId;
    }

    public int getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
}
