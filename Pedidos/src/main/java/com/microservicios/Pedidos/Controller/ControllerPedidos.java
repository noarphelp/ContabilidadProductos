package com.microservicios.Pedidos.Controller;

import com.microservicios.Pedidos.Model.Pedidos;
import com.microservicios.Pedidos.Service.ServicePedidos;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/pedidos")
public class ControllerPedidos {

    private ServicePedidos servicePedidos;

    public ControllerPedidos(ServicePedidos servicePedidos) {
        this.servicePedidos = servicePedidos;
    }


    @GetMapping("/all")
    @Operation(summary = "Obtener todos los pedidos", description = "Obtener todos los pedidos de la base de datos")
    public List<Pedidos> allPedidos() {


        return servicePedidos.allPedidos();
    }

    @PostMapping
    public void newPedidoPut(@RequestBody Pedidos pedidos) {

        servicePedidos.newPedido(pedidos);

    }
}