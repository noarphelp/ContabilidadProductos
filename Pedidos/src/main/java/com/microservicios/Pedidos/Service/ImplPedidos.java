package com.microservicios.Pedidos.Service;

import com.microservicios.Pedidos.Model.Pedidos;

import java.util.List;

public interface ImplPedidos {

    List<Pedidos>allPedidos();
    void newPedido(Pedidos pedidos);
}
