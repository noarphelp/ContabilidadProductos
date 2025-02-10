package com.microservicios.Pedidos.Service;

import com.microservicios.Pedidos.Model.Pedidos;
import com.microservicios.Pedidos.ProductoDTO;
import com.microservicios.Pedidos.Repository.PedidoRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
@Service
public class ServicePedidos implements ImplPedidos{

    private PedidoRepository pedidoRepository;
    private RestClient restClient;
    private String url = "http://servicio-productos/productos/";


    public ServicePedidos(PedidoRepository pedidoRepository, RestClient.Builder restClientBuilder) {
        this.pedidoRepository = pedidoRepository;
        this.restClient = restClientBuilder.build();

    }

    @Override
    public List<Pedidos> allPedidos() {
        Pedidos pedidos= new Pedidos();
       double total= pedidos.getTotal();


        return pedidoRepository.findAll();
    }


    @Override
    public void newPedido(Pedidos pedidos) {
        int codProd = pedidos.getCodProducto();
        int stock = pedidos.getUnidades();


        ProductoDTO productoDTO = restClient.get()
                .uri(url + codProd)
                .retrieve()
                .body(ProductoDTO.class);

        if (productoDTO == null) {
            throw new RuntimeException("Producto no encontrado con ID: " + codProd);
        }

        double total =productoDTO.getPrecioUnitario()*stock;
        pedidos.setTotal(total);

        restClient.put()
                .uri(url+codProd )
                .body(stock)
                .retrieve()
                .toBodilessEntity();

        pedidoRepository.save(pedidos);

    }
}
