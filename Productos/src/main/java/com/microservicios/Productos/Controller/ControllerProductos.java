package com.microservicios.Productos.Controller;

import com.microservicios.Productos.Model.Productos;
import com.microservicios.Productos.Service.ServiceProductos;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ControllerProductos {

    private ServiceProductos serviceProductos;

    public ControllerProductos(ServiceProductos serviceProductos) {
        this.serviceProductos = serviceProductos;
    }

    @CrossOrigin("*")
    @GetMapping("/all")
    public List<Productos>allProductos(){
        return serviceProductos.allProductos();

    }

    @CrossOrigin("*")
    @GetMapping("/{cod}")
    public Optional<Productos>productId(@PathVariable("cod")int codProducto){

        return Optional.ofNullable(serviceProductos.productoId(codProducto)).orElseThrow(null);
    }



    @GetMapping("/precioUnid/{cod}")
    public double precioUnitario(@PathVariable("cod")int codProducto){

        return Optional.ofNullable(serviceProductos.precioUnitario(codProducto)).orElseThrow(null);
    }


    @PutMapping("/{cod}")
    public void actualizar(@PathVariable("cod") int codProducto,@RequestBody int stock){

        serviceProductos.actualizarStock(codProducto,stock);
    }


    @CrossOrigin(origins = "http://localhost/8000") //permitir solo acceso desde productos
    @PostMapping
    public List<Productos>newProduct(@RequestBody Productos productos){

        serviceProductos.newProducto(productos);

        return serviceProductos.allProductos();

    }

    @CrossOrigin(origins = "http://localhost/8000") //permitir solo acceso desde productos
    @PutMapping("/admin/{cod}")
    public List<Productos> actualizarAdmin(@PathVariable("cod") int codProducto,@RequestParam int stock){

        serviceProductos.actualizarStockAdmin(codProducto,stock);

        return serviceProductos.allProductos();
    }

}
