package com.microservicio.Vista.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class VistaController {

    @GetMapping("/all")
    public String allPedidos(){

        return "Pedidos";
    }


    @GetMapping("/new")
    public String newPedido(){

        return "newpedido";
    }

}
