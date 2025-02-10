package com.microservicios.Productos.Service;

import com.microservicios.Productos.Model.Productos;
import com.microservicios.Productos.Repository.RepositoryProductos;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceProductos implements ImplService{

    private RepositoryProductos repositoryProductos;

    public ServiceProductos(RepositoryProductos repositoryProductos) {
        this.repositoryProductos = repositoryProductos;

    }

    @Override
    public List<Productos> allProductos() {
        return repositoryProductos.findAll();
    }

    @Override
    public Optional<Productos> productoId(int codProducto) {
        return Optional.ofNullable(repositoryProductos.findById(codProducto).orElseThrow(null));
    }


    @Override
    public Double precioUnitario(int codProducto) {
        Optional<Productos> cod= repositoryProductos.findById(codProducto);
        Productos productos= cod.get();

        return Optional.ofNullable(productos.getPrecioUnitario()).orElseThrow(null);
    }

    @Override
    public void actualizarStock(int codProducto ,int stock) {
        Optional<Productos> cod= repositoryProductos.findById(codProducto);

        if(cod.isPresent()){
            Productos producto=cod.get();
            // Verificar si hay suficiente stock
            if (producto.getStock() >= stock) {
                producto.setStock(producto.getStock() - stock);
                repositoryProductos.save(producto);
            } else {
                throw new RuntimeException("Stock insuficiente para el producto con ID: " + codProducto);
            }
        } else {
            throw new RuntimeException("Producto no encontrado con ID: " + codProducto);
        }
    }
    @Override
    public void newProducto(Productos productos) {

        repositoryProductos.save(productos);

    }


    public void actualizarStockAdmin(int codProducto ,int stock) {
        Optional<Productos> cod= repositoryProductos.findById(codProducto);

        if(cod.isPresent()) {
            Productos producto = cod.get();
            producto.setStock(stock);

            repositoryProductos.save(producto);
        }
    }

}
