package com.microservicios.Productos.Service;

import com.microservicios.Productos.Model.Productos;

import java.util.List;
import java.util.Optional;

public interface ImplService {

    List<Productos>allProductos();

    Optional<Productos>productoId(int codProducto);

    void actualizarStock(int codProducto,int cantidad);

    void newProducto(Productos productos);

    Double precioUnitario(int codProducto);

}
