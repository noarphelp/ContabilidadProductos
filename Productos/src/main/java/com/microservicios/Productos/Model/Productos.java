package com.microservicios.Productos.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Productos {

    @Id
    private int codProducto;
    private String producto;
    private double precioUnitario;
    private int stock;

    public Productos(int codProducto, String producto, double precioUnitario, int stock) {
        this.codProducto = codProducto;
        this.producto = producto;
        this.precioUnitario = precioUnitario;
        this.stock = stock;
    }

    public Productos() {
    }

    public int getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
